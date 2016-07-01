package dpiki.notificator;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

public class MarketWatcherService extends Service {
    MarketWatcherBackgroundThread thread;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }

    @Override
    public void onCreate() {
        thread = new MarketWatcherBackgroundThread(this);
        thread.start();
    }

    @Override
    public void onDestroy() {
        thread.interrupt();
    }
}
