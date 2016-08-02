package dpiki.notificator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import dpiki.notificator.network.SyncMarketService;

public class BootReceiver extends BroadcastReceiver {
    public BootReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Arbeiten", Toast.LENGTH_SHORT).show();
        SyncMarketService.rerunNotificationService(context);
    }
}
