package dpiki.notificator;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Lenovo on 01.07.2016.
 */
public class MarketWatcherBackgroundThread extends Thread {
    static String TAG = "BGThread";
    static String KEY_LAST_UPDATE = "lastUpdate";
    static String KEY_NOTIF_ID = "notificationId";

    Context context;

    public MarketWatcherBackgroundThread(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();

        String lastUpdate = pref.getString(KEY_LAST_UPDATE, "");
        int notifyId = pref.getInt(KEY_NOTIF_ID, 0);

        try {
            while (true) {
                ArrayList<Phone> phones = MarketDatabaseWorker.getLastPhones(lastUpdate);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date currDate = new Date();
                lastUpdate = sdf.format(currDate);
                editor.putString(KEY_LAST_UPDATE, lastUpdate);
                editor.apply();

                ArrayList<MarketClient> clients = ClientDatabaseWorker.clients();
                ArrayList<Recommendation> recommendations = filter(phones, clients);

                NotificationManager nm =
                        (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

                for (Recommendation i : recommendations) {
                    NotificationCompat.Builder builder =
                            new NotificationCompat.Builder(context)
                            .setSmallIcon(android.R.drawable.ic_dialog_alert)
                            .setWhen(System.currentTimeMillis())
                            .setDefaults(Notification.DEFAULT_ALL)
                            .setTicker("Есть рекомендация")
                            .setContentTitle("Есть рекомендация")
                            .setContentText("Клиент: " + i.client.getName() +
                                            "\nТелефон: " + i.phone.getName());
                    Notification n = builder.build();
                    nm.notify(notifyId, n);
                    notifyId++;
                    editor.putInt(KEY_NOTIF_ID, notifyId);
                    editor.apply();
                }

                Thread.sleep(60000);
            }
        }
        catch (InterruptedException e) {
            Log.d(TAG, "Thread stopped");
        }
    }

    ArrayList<Recommendation> filter(ArrayList<Phone> phones, ArrayList<MarketClient> filter) {
        Phone phone = new Phone(0, "Lenovo", "param1", "param2", "param3");
        MarketClient client = new MarketClient(0, "Gosha", "param1", "param2", "param3");
        Recommendation rec = new Recommendation(client, phone);
        ArrayList<Recommendation> ret_val = new ArrayList<>();

        ret_val.add(rec);

        return ret_val;
    }
}
