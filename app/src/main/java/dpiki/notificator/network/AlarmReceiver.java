package dpiki.notificator.network;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

public class AlarmReceiver extends WakefulBroadcastReceiver {
    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context, SyncMarketService.class);
        startWakefulService(context, serviceIntent);
    }
}
