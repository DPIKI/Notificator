package dpiki.notificator;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SyncMarketService extends IntentService {
    public static final String TAG = "SyncMarket";

    public static final String PREF_KEY_NOTIFY_ID = "notifyId";
    public static final String PREF_KEY_LAST_DATE = "lastDate";
    public static final String PREF_KEY_IP = "ipAddress";

    RequestQueue queue;

    DataFetcher<?, ?> fetcher;

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
        fetcher.fetch();
    }
/*
    ArrayList<Phone> extractResponse(JSONObject object) {
        Log.d(TAG, "Extracting phones...");
   }

    ArrayList<Recommendation> filterNewPhones(ArrayList<Phone> phones,
                                              ArrayList<MarketClient> filter) {
        ArrayList<Recommendation> recommendation = new ArrayList<>();

        for (Phone i : phones) {
            for (MarketClient j : filter) {
                if ((i.getParam1().equals(j.getPref1())) &&
                    (i.getParam2().equals(j.getPref2())) &&
                    (i.getParam3().equals(j.getPref3()))) {
                    recommendation.add(new Recommendation(j, i));
                }
            }
        }
        return recommendation;
    }

    void notifyUser(ArrayList<Recommendation> recommendations) {

        DatabaseHelper.addNotifications(recommendations,this);
        int nclients = DatabaseHelper.getNumberNotifyClients(this);
        int nphones = DatabaseHelper.getNumberNotifyPhones(this);
        NotificationManager nm =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        Integer notifyId = pref.getInt(PREF_KEY_NOTIFY_ID, 0);

        Log.d(TAG, "Notify id : " + notifyId);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.ic_dialog_alert)
                        .setWhen(System.currentTimeMillis())
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setContentTitle("Есть рекомендация")
                        .setContentText("Для " + nclients +
                                " клиентов  есть " + nphones +  " рекомендации");
        Notification n = builder.build();
        nm.notify(notifyId, n);
        notifyId++;
        editor.putInt(PREF_KEY_NOTIFY_ID, notifyId);
        editor.apply();
        /*
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
        }*/
    }

    void requestNewPhones(String ip, String lastDate) {
        try {
            Log.d(TAG, "requestNewPhones");

            RequestFuture<JSONObject> future = RequestFuture.newFuture();
            JsonObjectRequest request = new JsonObjectRequest(
                    JsonObjectRequest.Method.GET,
                    "http://" + ip + "/get_new_phones.php?date="
                            + lastDate.replace(" ", "%20"),
                    future, future);
            queue.add(request);

            JSONObject response = future.get(10, TimeUnit.SECONDS);
            ArrayList<Phone> phones = extractResponse(response);

            if (!phones.isEmpty()) {
                Date last = findLastAddedPhone(phones);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                lastDate = sdf.format(last);
                Log.d(TAG, "saving new last date: " + lastDate);
                saveLastDate(lastDate);

                ArrayList<MarketClient> clients = DatabaseHelper.readClients(this);
                ArrayList<Recommendation> recommendations = filterNewPhones(phones, clients);
                notifyUser(recommendations);
            }
        } catch (InterruptedException |
                 ExecutionException |
                 TimeoutException e) {
            e.printStackTrace();
        }
    }

    void requestLastDate(String ip) {
        try {
            RequestFuture<JSONObject> future = RequestFuture.newFuture();
            JsonObjectRequest request = new JsonObjectRequest(
                    JsonObjectRequest.Method.GET,
                    "http://" + ip + "/get_last_date.php",
                    future, future);
            queue.add(request);

            JSONObject response = future.get(10, TimeUnit.SECONDS);
            if (response.getBoolean(JSON_KEY_SUCCESS)) {
                String lastDate = response.getString(JSON_KEY_LAST_DATE);
                saveLastDate(lastDate);
            }
        } catch (InterruptedException |
                ExecutionException |
                TimeoutException |
                JSONException e) {
            e.printStackTrace();
        }
    }


    void saveLastDate(String lastDate) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_KEY_LAST_DATE, lastDate);
        editor.apply();
    }*/
}
