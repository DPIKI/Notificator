package dpiki.notificator.network;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

public class SyncMarketService extends Service {
    public static final String TAG = "SyncMarket";

    Handler backgroundHandler;

    Runnable fetchRunnable = new Runnable() {
        @Override
        public void run() {
            MyFetcher fetcher = new MyFetcher(SyncMarketService.this);
            fetcher.fetch();
            backgroundHandler.postDelayed(this, 4000);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentText("Service")
                .setContentTitle("Title")
                .setTicker("Ticker")
                .setSmallIcon(android.R.drawable.btn_default);
        startForeground(0, builder.build());

        PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "SyncServieWakeLock");
        wl.acquire();

        HandlerThread handlerThread = new HandlerThread("SyncServiceHandlerThread");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        while (looper == null) {
            looper = handlerThread.getLooper();
        }

        backgroundHandler = new Handler(looper);
        backgroundHandler.post(fetchRunnable);
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
}
