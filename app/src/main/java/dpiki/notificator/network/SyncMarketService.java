package dpiki.notificator.network;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import junit.framework.Assert;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;

import dpiki.notificator.App;
import dpiki.notificator.DatabaseUtils;
import dpiki.notificator.PrefManager;
import dpiki.notificator.data.Recommendation;

public class SyncMarketService extends Service {
    public static final String TAG = "SyncMS";

    public static int FOREGROUND_NOTIFICATION_ID = 1;
    public static int RECOMMENDATION_NOTIFICATION_ID = 2;

    public static final String ACTION_START_RECEIVE = "dpiki.notificator.action.START_RECEIVE";
    public static final String ACTION_STOP_RECEIVE = "dpiki.notificator.action.STOP_RECEIVE";
    public static final String ACTION_NEW_RECOMMENDATIONS = "dpiki.notificator.action.NEW_RECOMMENDATION";

    @Inject
    public DatabaseUtils mDatabaseUtils;

    @Inject
    public PrefManager mPrefManager;

    @Inject
    public SickBastard mSickBastard;

    private Handler mBackgroundHandler;
    private PowerManager.WakeLock mWakeLock;
    private boolean mIsThreadRunning;

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
            List<Recommendation> recommendations = mSickBastard.getRecommendations();
            handleRecommendations(recommendations);
            mBackgroundHandler.postDelayed(fetchData, 5 * 1000);
        }

        private void handleRecommendations(List<Recommendation> r) {
            if (r.isEmpty())
                return;

            Set<String> uniqueClients = new TreeSet<>();
            Set<Long> uniqueProducts = new TreeSet<>();

            for (Recommendation i : r) {
                uniqueClients.add(i.type + i.idRequisition);
                uniqueProducts.add(i.idRealEstate);
            }

            Notification n = new NotificationCompat.Builder(SyncMarketService.this)
                    .setTicker("Ticker")
                    .setContentText("Для " + uniqueClients.size() + " клиентов " +
                            uniqueProducts.size() + " продукта")
                    .setSmallIcon(android.R.drawable.ic_btn_speak_now)
                    .setContentTitle("Title")
                    .setWhen(System.currentTimeMillis())
                    .setDefaults(Notification.DEFAULT_ALL)
                    .build();
            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            nm.notify(RECOMMENDATION_NOTIFICATION_ID, n);

            sendBroadcast(new Intent(ACTION_NEW_RECOMMENDATIONS));
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
        Assert.assertEquals("\nYou need to use it everywhere : \n"
                        + "SimpleDateFormat sdf = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\")\n"
                        + "HH - upperCase : 24h\n"
                        + "hh - lowerCase : 12h\n",
                true,false);
        App.getInstance().inject(this);
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
        mPrefManager.putReceiveNotificationsFlag(true);
        Intent intent = new Intent(ACTION_START_RECEIVE);
        sendBroadcast(intent);
    }

    private void stopReceiveNotifications() {
        stopBackgroundThread();
        mPrefManager.putReceiveNotificationsFlag(false);
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
        // TODO : need more clever solution
        PrefManager prefManager = new PrefManager(context);
        return prefManager.getReceiveNotificationsFlag();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
