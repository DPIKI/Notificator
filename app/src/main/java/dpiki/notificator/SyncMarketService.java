package dpiki.notificator;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    public static final String JSON_KEY_SUCCESS = "success";
    public static final String JSON_KEY_PHONES = "phones";
    public static final String JSON_KEY_ID = "pid";
    public static final String JSON_KEY_NAME = "name";
    public static final String JSON_KEY_PARAM1 = "param1";
    public static final String JSON_KEY_PARAM2 = "param2";
    public static final String JSON_KEY_PARAM3 = "param3";
    //TODO: Rename JSON_KEY_DATE
    public static final String JSON_KEY_DATE = "creation_date";
    public static final String JSON_KEY_LAST_DATE = "lastDate";

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
        String lastDate = pref.getString(PREF_KEY_LAST_DATE, "");

        if (!lastDate.equals("")) {
            requestNewPhones(lastDate);
        } else {
            requestLastDate();
        }
    }

    ArrayList<Phone> extractResponse(JSONObject object) {
        ArrayList<Phone> phones = new ArrayList<>();
        try {
            if (object.getBoolean(JSON_KEY_SUCCESS)) {
                JSONArray json = object.getJSONArray(JSON_KEY_PHONES);

                for (int i = 0; i < json.length(); i++) {
                    JSONObject jsonObject = json.getJSONObject(i);

                    SimpleDateFormat sdf = new SimpleDateFormat();
                    sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
                    Date date = sdf.parse(jsonObject.getString(JSON_KEY_DATE));

                    Phone phone = new Phone(
                            jsonObject.getInt(JSON_KEY_ID),
                            jsonObject.getString(JSON_KEY_NAME),
                            jsonObject.getString(JSON_KEY_PARAM1),
                            jsonObject.getString(JSON_KEY_PARAM2),
                            jsonObject.getString(JSON_KEY_PARAM3),
                            date);
                    phones.add(phone);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            phones.clear();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return phones;
    }

    ArrayList<Recommendation> filterNewPhones(ArrayList<Phone> phones,
                                              ArrayList<MarketClient> filter) {
        ArrayList<Recommendation> recommendation = new ArrayList<>();

        for (Phone i : phones) {
            for (MarketClient j : filter) {
                if ((i.getParam1().equals(j.getPref1()) || i.getParam1().isEmpty()) &&
                        (i.getParam2().equals(j.getPref2()) || i.getParam2().isEmpty()) &&
                                (i.getParam3().equals(j.getPref3()) || i.getParam3().isEmpty())) {
                    recommendation.add(new Recommendation(j, i));
                }
            }
        }
        return recommendation;
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

    void requestNewPhones(String lastDate) {
        try {
            RequestFuture<JSONObject> future = RequestFuture.newFuture();
            JsonObjectRequest request = new JsonObjectRequest(
                    JsonObjectRequest.Method.GET,
                    "http://192.168.137.144/get_new_phones.php?date="
                            + lastDate.replace(" ", "%20"),
                    future, future);
            queue.add(request);

            JSONObject response = future.get(10, TimeUnit.SECONDS);
            ArrayList<Phone> phones = extractResponse(response);

            if (!phones.isEmpty()) {
                Date last = findLastAddedPhone(phones);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                lastDate = sdf.format(last);
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

    void requestLastDate() {
        try {
            RequestFuture<JSONObject> future = RequestFuture.newFuture();
            JsonObjectRequest request = new JsonObjectRequest(
                    JsonObjectRequest.Method.GET,
                    "http://192.168.137.144/get_last_date.php",
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

    Date findLastAddedPhone(ArrayList<Phone> phones) {
        return Collections.max(phones, new Comparator<Phone>() {
            @Override
            public int compare(Phone lhs, Phone rhs) {
                if (lhs.getDate().after(rhs.getDate())) {
                    return 1;
                } else if (rhs.getDate().after(lhs.getDate())) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }).getDate();
    }

    void saveLastDate(String lastDate) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_KEY_LAST_DATE, lastDate);
        editor.apply();
    }
}
