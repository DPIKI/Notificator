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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
        @Override
        public void run() {
            SharedPreferences pref = getSharedPreferences(PREF_NAME, 0);
            String strCreator = pref.getString(PREF_KEY_CREATOR, "");
            Log.d(TAG, "String read : " + strCreator);

            if (!strCreator.isEmpty()) {
                try {
                    byte[] byteCreator = new byte[strCreator.length() / 2];
                    for (int i = 0; i < strCreator.length(); i += 2) {
                        byteCreator[i >> 1] =
                                (byte) ((Character.digit(strCreator.charAt(i), 16) << 4) +
                                        (Character.digit(strCreator.charAt(i + 1), 16)));
                    }
                    InputStream is = new ByteArrayInputStream(byteCreator);
                    ObjectInputStream in = new ObjectInputStream(is);

                    Log.d(TAG, "Starting deserialization...");

                    DataFetcherCreator creator = (DataFetcherCreator) in.readObject();

                    Log.d(TAG, "Creator deserialization completed");

                    DataFetcher<?, ?> fetcher = creator.createFetcher(SyncMarketService.this);
                    fetcher.fetch();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            mBackgroundHandler.postDelayed(fetchData, 5 * 1000);
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
        stopBackgroundThread();

        SharedPreferences pref = getSharedPreferences(PREF_NAME, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(PREF_KEY_RECEIVE_NOTIFICATIONS, false);
        editor.apply();

        Intent intent = new Intent(ACTION_STOP_RECEIVE);
        sendBroadcast(intent);
    }

    private void startBackgroundThread() {
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

            Log.d(TAG, "String creator : " + os.toString());

            byte[] byteObject = os.toByteArray();
            String strObject = "";
            for (byte i : byteObject) {
                strObject += String.format("%02X", i);
            }

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
