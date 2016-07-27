package dpiki.notificator.network;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dpiki.notificator.data.ClientLaptops;
import dpiki.notificator.data.ClientPhones;
import dpiki.notificator.data.ClientResponse;
import dpiki.notificator.data.laptop.Laptop;
import dpiki.notificator.data.laptop.LaptopRecommendation;
import dpiki.notificator.data.laptop.LaptopResponse;
import dpiki.notificator.data.phone.Phone;
import dpiki.notificator.data.phone.PhoneRecommendation;
import dpiki.notificator.data.phone.PhoneResponse;
import dpiki.notificator.data.Recommendation;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SyncMarketService extends Service {
    public static final String TAG = "SyncMS";

    public static final String PREF_KEY_RECEIVE_NOTIFICATIONS = "receive_notifications";
    public static final String PREF_KEY_CREATOR = "DataFetcherCreator";
    public static final String PREF_NAME = "SyncMarketService";

    public static int FOREGROUND_NOTIFICATION_ID = 1;

    public static final String ACTION_START_RECEIVE = "dpiki.notificator.action.START_RECEIVE";
    public static final String ACTION_STOP_RECEIVE = "dpiki.notificator.action.STOP_RECEIVE";

    Handler mBackgroundHandler;
    PowerManager.WakeLock mWakeLock;
    boolean mIsThreadRunning;

    Runnable initBackgroundThread = new Runnable() {
        @Override
        public void run() {
            PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "FetcherThreadLock");
            if (!mWakeLock.isHeld())
                mWakeLock.acquire();

            NotificationCompat.Builder builder = new NotificationCompat.Builder(SyncMarketService.this)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(android.R.drawable.btn_default)
                    .setTicker("Ticker")
                    .setContentText("Text")
                    .setContentTitle("Title");
            Notification n = builder.build();
            startForeground(FOREGROUND_NOTIFICATION_ID, n);
        }
    };

    Runnable fetchData = new Runnable() {
        private ServerApi api;
        @Override
        public void run() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ServerApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            api = retrofit.create(ServerApi.class);

            try {
                Call<ClientResponse> clientsRequest = api.getClients(0);
                Response<ClientResponse> clientResponse = clientsRequest.execute();

                ClientResponse clients = clientResponse.body();
                if (clients == null)
                    throw new Exception("Failed to parse response with clients");

                List<Recommendation> recommendations = new ArrayList<>();

                List<ClientLaptops> laptopClients = clients.laptops;
                if (!laptopClients.isEmpty()) {
                    fetchLaptops(clients.laptops, recommendations);
                    fetchPhones(clients.phones, recommendations);
                }

                if (!recommendations.isEmpty()) {
                    handleRecommendations(recommendations);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            mBackgroundHandler.postDelayed(fetchData, 5 * 1000);
        }

        private void fetchLaptops(List<ClientLaptops> clients, List<Recommendation> r) {
            try {
                Call<LaptopResponse> laptopRequest = api.getLaptops(new Date());
                Response<LaptopResponse> laptopResponse = laptopRequest.execute();

                LaptopResponse laptops = laptopResponse.body();
                if (laptops == null)
                    throw new Exception("Failed to parse response with laptops");

                if (!laptops.success)
                    throw new Exception("Invalid request");

                for (Laptop i : laptops.laptops) {
                    for (ClientLaptops j : clients) {
                        if (i.param11.equals(j.pref11) &&
                                i.param12.equals(j.pref12) &&
                                i.param13.equals(j.pref13) &&
                                i.param14.equals(j.pref14)) {
                            r.add(new LaptopRecommendation(j, i));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void fetchPhones(List<ClientPhones> clients, List<Recommendation> r) {
            try {
                Call<PhoneResponse> phoneRequest = api.getPhones(new Date());
                Response<PhoneResponse> phoneResponse = phoneRequest.execute();

                PhoneResponse phones = phoneResponse.body();
                if (phones == null)
                    throw new Exception("Failed to parse response with laptops");

                if (!phones.success)
                    throw new Exception("Invalid request");

                for (Phone i : phones.phones) {
                    for (ClientPhones j : clients) {
                        if (i.param1.equals(j.pref1) &&
                                i.param2.equals(j.pref2) &&
                                i.param3.equals(j.pref3)) {
                            r.add(new PhoneRecommendation(j, i));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void handleRecommendations(List<Recommendation> r) {

        }
    };

    Runnable cleanUpBackgroundThread = new Runnable() {
        @Override
        public void run() {
            if (mWakeLock.isHeld())
                mWakeLock.release();
            stopForeground(true);
            mBackgroundHandler.getLooper().quit();
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        rerunNotificationService(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopBackgroundThread();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction().equals(ACTION_START_RECEIVE)) {
            startReceiveNotifications();
        } else if (intent.getAction().equals(ACTION_STOP_RECEIVE)) {
            stopReceiveNotifications();
        }
        return START_STICKY;
    }

    private void startReceiveNotifications() {
        startBackgroundThread();

        SharedPreferences pref = getSharedPreferences(PREF_NAME, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(PREF_KEY_RECEIVE_NOTIFICATIONS, true);
        editor.apply();

        Intent intent = new Intent(ACTION_START_RECEIVE);
        sendBroadcast(intent);
    }

    private void stopReceiveNotifications() {
        Log.d(TAG, "stop isThreadRunning : " + mIsThreadRunning);

        stopBackgroundThread();

        SharedPreferences pref = getSharedPreferences(PREF_NAME, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(PREF_KEY_RECEIVE_NOTIFICATIONS, false);
        editor.apply();

        Intent intent = new Intent(ACTION_STOP_RECEIVE);
        sendBroadcast(intent);
    }

    private void startBackgroundThread() {
        Log.d(TAG, "start isThreadRunning : " + mIsThreadRunning);

        if (!mIsThreadRunning) {
            HandlerThread thread = new HandlerThread("SyncMarketServiceBackgroundThread");
            thread.start();
            Looper looper = thread.getLooper();
            while (looper == null)
                looper = thread.getLooper();
            mBackgroundHandler = new Handler(looper);
            mBackgroundHandler.post(initBackgroundThread);
            mBackgroundHandler.post(fetchData);
            mIsThreadRunning = true;
        }
    }

    private void stopBackgroundThread() {
        if (mIsThreadRunning) {
            mBackgroundHandler.post(cleanUpBackgroundThread);
            mIsThreadRunning = false;
        }
    }

    public static void startNotificationService(Context context) {
        Intent startIntent = new Intent(context, SyncMarketService.class);
        startIntent.setAction(ACTION_START_RECEIVE);
        context.startService(startIntent);
    }

    public static void stopNotificationService(Context context) {
        Intent stopIntent = new Intent(context, SyncMarketService.class);
        stopIntent.setAction(ACTION_STOP_RECEIVE);
        context.startService(stopIntent);
    }

    public static void rerunNotificationService(Context context) {
        if (serverStatus(context)) {
            startNotificationService(context);
        }
    }

    public static boolean serverStatus(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, 0);
        return pref.getBoolean(PREF_KEY_RECEIVE_NOTIFICATIONS, false);
    }

    public static boolean configureService(Context context, DataFetcherCreator creator) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(os);

            Log.d(TAG, "Starting serializing...");

            out.writeObject(creator);

            Log.d(TAG, "Creator serialized");

            SharedPreferences pref = context.getSharedPreferences(PREF_NAME, 0);
            SharedPreferences.Editor editor = pref.edit();

            byte[] byteObject = os.toByteArray();
            String strObject = "";
            for (byte i : byteObject) {
                strObject += String.format("%02X", i);
            }

            Log.d(TAG, "String creator : " + strObject);

            editor.putString(PREF_KEY_CREATOR, strObject);
            editor.apply();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
