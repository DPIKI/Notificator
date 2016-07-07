package dpiki.notificator.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import dpiki.notificator.network.SyncMarketService;

public class AlarmReceiver extends BroadcastReceiver {
    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        SyncMarketService.fetchPhoneData(context);
    }
}
