package dpiki.notificator.network;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

public class SyncMarketService extends IntentService {
    public static final String TAG = "SyncMarket";

    public SyncMarketService() {
        super("SyncMarketService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static void fetchPhoneData(Context context) {
        Intent intent = new Intent(context, SyncMarketService.class);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        MyFetcher fetcher = new MyFetcher(this);
        fetcher.fetch();
        AlarmReceiver.completeWakefulIntent(intent);
    }
}
