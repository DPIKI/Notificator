package dpiki.notificator.network;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class SyncMarketService extends Service {
    public static final String TAG = "SyncMarket";

    public static final String PREF_KEY_RECEIVE_NOTIFICATIONS = "receive_notifications";

    public static int FOREGROUND_NOTIFICATION_ID = 1;

    Handler mBackgroundHandler;
    PowerManager.WakeLock mWakeLock;
    boolean mIsThreadRunning;

    Runnable initBackgroundThread = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "initBackgroundThread");

            PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "FetcherThreadLock");
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
            Log.d(TAG, "fetchData");

            MyFetcher fetcher = new MyFetcher(SyncMarketService.this);
            fetcher.fetch();
            mBackgroundHandler.postDelayed(fetchData, 5 * 1000);
        }
    };

    Runnable cleanUpBackgroundThread = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "cleanUpBackgroundThread");

            mWakeLock.release();
            stopForeground(true);
            mBackgroundHandler.getLooper().quit();
        }
    };

    SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener
            = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            Log.d(TAG, "onSharedPreferenceChanged");

            if (sharedPreferences.getBoolean(PREF_KEY_RECEIVE_NOTIFICATIONS, false)) {
                startReceiveNotifications();
            } else {
                stopReceiveNotifications();
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate");

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        pref.registerOnSharedPreferenceChangeListener(sharedPreferenceChangeListener);
        boolean needRunning = pref.getBoolean(PREF_KEY_RECEIVE_NOTIFICATIONS, false);

        mIsThreadRunning = false;
        if (needRunning) {
            startReceiveNotifications();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void startReceiveNotifications() {
        Log.d(TAG, "startReceiveNotifications");

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

    private void stopReceiveNotifications() {
        Log.d(TAG, "stopReceiveNotifications");

        mBackgroundHandler.post(cleanUpBackgroundThread);
        mIsThreadRunning = false;
    }
}
