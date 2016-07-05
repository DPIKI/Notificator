package dpiki.notificator;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import dpiki.notificator.data.MarketClient;
import dpiki.notificator.data.Phone;
import dpiki.notificator.data.Recommendation;

public class SyncMarketService extends IntentService {
    public static final String TAG = "SyncMarket";
    public static final String PREF_KEY_NOTIFY_ID = "notifyId";
    public static final String PREF_KEY_LAST_DATE = "lastDate";

    RequestQueue queue;

    public SyncMarketService() {
        super("SyncMarketService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        queue = Volley.newRequestQueue(this);
    }

    public static void fetchPhoneData(Context context) {
        Intent intent = new Intent(context, SyncMarketService.class);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();

        String lastDate = pref.getString(PREF_KEY_LAST_DATE, "");
        if (!lastDate.equals("")) {
            RequestFuture<JSONObject> future = RequestFuture.newFuture();
            JsonObjectRequest request = new JsonObjectRequest(
                    JsonObjectRequest.Method.GET,
                    "http://192.168.137.144/get_new_phones.php?date=2016-07-04%2014:00:00", future, future);
            queue.add(request);

            try {
                JSONObject response = future.get(10, TimeUnit.SECONDS);
                ArrayList<Phone> phones = extractResponse(response);
                ArrayList<MarketClient> clients = readClients();
                ArrayList<Recommendation> recommendations = filterNewPhones(phones, clients);
                notifyUser(recommendations);
                Log.d(TAG, response.toString());
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
            }
        } else {

        }
    }

    ArrayList<Phone> extractResponse(JSONObject object) {
        return new ArrayList<>();
    }

    ArrayList<MarketClient> readClients() {
        return new ArrayList<>();
    }

    ArrayList<Recommendation> filterNewPhones(ArrayList<Phone> phones,
                                              ArrayList<MarketClient> filter) {
        return new ArrayList<>();
    }

    void notifyUser(ArrayList<Recommendation> recommendations) {
        NotificationManager nm =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        Integer notifyId = pref.getInt(PREF_KEY_NOTIFY_ID, 0);

        for (Recommendation i : recommendations) {
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(android.R.drawable.ic_dialog_alert)
                            .setWhen(System.currentTimeMillis())
                            .setDefaults(Notification.DEFAULT_ALL)
                            .setContentTitle("Есть рекомендация")
                            .setContentText("Клиент: " + i.client.getName() +
                                    "\nТелефон: " + i.phone.getName());
            Notification n = builder.build();
            nm.notify(notifyId, n);
            notifyId++;
            editor.putInt(PREF_KEY_NOTIFY_ID, notifyId);
            editor.apply();
        }
    }
}
