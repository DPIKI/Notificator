package dpiki.notificator;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.util.ArraySet;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import dpiki.notificator.data.MarketClient;
import dpiki.notificator.data.Phone;

/**
 * Created by Lenovo on 07.07.2016.
 */
public class MyFetcher extends DataFetcher<Phone, MarketClient> {
    public static final String TAG = "MyFetcher";

    public static final String PREF_KEY_NOTIFY_ID = "notifyId";
    public static final String PREF_KEY_LAST_DATE = "lastDate";
    public static final String PREF_KEY_IP = "ipAddress";

    public static final String JSON_KEY_SUCCESS = "success";
    public static final String JSON_KEY_PHONES = "phones";
    public static final String JSON_KEY_ID = "pid";
    public static final String JSON_KEY_NAME = "name";
    public static final String JSON_KEY_PARAM1 = "param1";
    public static final String JSON_KEY_PARAM2 = "param2";
    public static final String JSON_KEY_PARAM3 = "param3";
    public static final String JSON_KEY_CREATION_DATE = "creation_date";

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    RequestQueue queue;
    Context context;

    public MyFetcher(Context context) {
        this.context = context;
        queue = Volley.newRequestQueue(context);
    }

    @Override
    public ArrayList<Phone> loadItems() throws Exception {
        Log.d(TAG, "loading items...");

        Date currentDate = new Date();
        String strCurrentDate = sdf.format(currentDate);
        SharedPreferences pref = context.getSharedPreferences("MyFetcher", 0);
        String strLastFetch = pref.getString(PREF_KEY_LAST_DATE, strCurrentDate);
        Log.d(TAG, "date: " + strCurrentDate);

        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_KEY_LAST_DATE, strLastFetch);
        editor.apply();

        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                "http://192.168.137.110/get_new_phones.php?date=" + strLastFetch.replace(" ", "%20"),
                future, future);
        queue.add(request);
        JSONObject response = future.get(10, TimeUnit.SECONDS);

        ArrayList<Phone> ret = extractResponse(response);

        if (!ret.isEmpty()) {
            Date lastFetch = findLastAddedPhone(ret);
            strLastFetch = sdf.format(lastFetch);
            editor.putString(PREF_KEY_LAST_DATE, strLastFetch);
            editor.apply();
        }

        return ret;
    }

    @Override
    public ArrayList<MarketClient> loadFilters() throws Exception {
        Log.d(TAG, "Loading filters...");

        ArrayList<MarketClient> filters = DatabaseHelper.readClients(context);

        Log.d(TAG, "Filters : " + filters.size());

        return filters;
    }

    @Override
    public Boolean isMatch(Phone i, MarketClient f) {
        return (i.getParam1().equals(f.getPref1()) &&
                i.getParam2().equals(f.getPref2()) &&
                i.getParam3().equals(f.getPref3()));
    }

    @Override
    public void onNewRecommendations(ArrayList<Recommendation> recommendations) throws Exception{
        Log.d(TAG, "onNewRecommendation");

        DatabaseHelper.addNotifications(recommendations, context);

        TreeSet<Integer> phoneids = new TreeSet<>();
        TreeSet<Integer> clientids = new TreeSet<>();

        for (Recommendation i : recommendations) {
            phoneids.add(i.i.getId());
            clientids.add(i.f.getId());
        }

        int clientsCount = clientids.size();
        int phonesCount = phoneids.size();

        Log.d(TAG, "Clients count: " + clientsCount);
        Log.d(TAG, "Phones count: " + phonesCount);

        SharedPreferences pref = context.getSharedPreferences("MyFetcher", 0);
        Integer notifyId = pref.getInt(PREF_KEY_NOTIFY_ID, 0);
        Log.d(TAG, "Notify id : " + notifyId);

        NotificationManager nm =
                (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(android.R.drawable.ic_dialog_alert)
                        .setWhen(System.currentTimeMillis())
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setContentTitle("Есть рекомендация")
                        .setContentText("Для " + clientsCount +
                                " клиентов  есть " + phonesCount +  " рекомендации");
        Notification n = builder.build();
        nm.notify(notifyId, n);
        notifyId++;

        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_KEY_NOTIFY_ID, notifyId);
        editor.apply();
    }

    ArrayList<Phone> extractResponse(JSONObject response) throws Exception {
        Log.d(TAG, "extracting response...");

        ArrayList<Phone> phones = new ArrayList<>();
        if (response.getBoolean(JSON_KEY_SUCCESS)) {
            JSONArray json = response.getJSONArray(JSON_KEY_PHONES);
            for (int i = 0; i < json.length(); i++) {
                JSONObject jsonObject = json.getJSONObject(i);
                Date date = sdf.parse(jsonObject.getString(JSON_KEY_CREATION_DATE));
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

        Log.d(TAG, "Extracted phones: " + phones.size());

        return phones;
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

}
