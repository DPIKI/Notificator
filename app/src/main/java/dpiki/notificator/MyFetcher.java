package dpiki.notificator;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;

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
import dpiki.notificator.network.DataFetcher;
import dpiki.notificator.network.SyncMarketService;

/**
 * Created by Lenovo on 07.07.2016.
 */
public class MyFetcher extends DataFetcher<Phone, MarketClient> {
    public static final String ACTION_NEW_RECOMMENDATIONS = "dpiki.notificator.action.NEW_RECOMMENDATIONS";

    private static final String PREF_KEY_NOTIFY_ID = "notifyId";
    private static final String PREF_KEY_LAST_DATE = "lastDate";
    private static final String PREF_NAME = "MyFetcher";

    private static final String JSON_KEY_SUCCESS = "success";
    private static final String JSON_KEY_PHONES = "phones";
    private static final String JSON_KEY_ID = "pid";
    private static final String JSON_KEY_NAME = "name";
    private static final String JSON_KEY_PARAM1 = "param1";
    private static final String JSON_KEY_PARAM2 = "param2";
    private static final String JSON_KEY_PARAM3 = "param3";
    private static final String JSON_KEY_CREATION_DATE = "creation_date";

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private RequestQueue queue;
    private Context context;

    public MyFetcher(Context context) {
        this.context = context;
        queue = Volley.newRequestQueue(context);
    }

    @Override
    public ArrayList<Phone> loadItems() throws Exception {
        String strLastFetch = prefGetLastFetchDate();
        prefPutLastFetchDate(strLastFetch);
        JSONObject response = makeRequest(strLastFetch);
        ArrayList<Phone> ret = extractResponse(response);
        if (!ret.isEmpty()) {
            Date lastFetch = findLastAddedPhone(ret);
            strLastFetch = sdf.format(lastFetch);
            prefPutLastFetchDate(strLastFetch);
        }
        return ret;
    }

    @Override
    public ArrayList<MarketClient> loadFilters() throws Exception {
        return DatabaseHelper.readClients(context);
    }

    @Override
    public Boolean isMatch(Phone i, MarketClient f) {
        return (i.getParam1().equals(f.getPref1()) &&
                i.getParam2().equals(f.getPref2()) &&
                i.getParam3().equals(f.getPref3()));
    }

    @Override
    public void onNewRecommendations(ArrayList<Recommendation> recommendations) throws Exception{
        DatabaseHelper.addNotifications(recommendations, context);

        TreeSet<Integer> phoneids = new TreeSet<>();
        TreeSet<Integer> clientids = new TreeSet<>();

        for (Recommendation i : recommendations) {
            phoneids.add(i.i.getId());
            clientids.add(i.f.getId());
        }

        int clientsCount = clientids.size();
        int phonesCount = phoneids.size();

        notifyUser(clientsCount, phonesCount);

        context.sendBroadcast(new Intent(ACTION_NEW_RECOMMENDATIONS));
    }

    private ArrayList<Phone> extractResponse(JSONObject response) throws Exception {
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
        return phones;
    }

    private Date findLastAddedPhone(ArrayList<Phone> phones) {
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

    private void prefPutLastFetchDate(String date) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_KEY_LAST_DATE, date);
        editor.apply();
    }

    private String prefGetLastFetchDate() {
        Date currentDate = new Date();
        String strCurrentDate = sdf.format(currentDate);
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, 0);
        return pref.getString(PREF_KEY_LAST_DATE, strCurrentDate);
    }

    private int nextNotifyId() {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, 0);
        int ret_val = pref.getInt(PREF_KEY_NOTIFY_ID, 1);

        if (ret_val == SyncMarketService.FOREGROUND_NOTIFICATION_ID)
            ret_val++;

        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_KEY_NOTIFY_ID, ret_val + 1);
        editor.apply();

        return ret_val;
    }

    private void notifyUser(int clientsCount, int phonesCount) {
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(android.R.drawable.ic_dialog_alert)
                        .setWhen(System.currentTimeMillis())
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setContentTitle("Есть рекомендация")
                        .setContentText("Для " + clientsCount +
                                " клиентов  есть " + phonesCount +  " рекомендации");
        Notification n = builder.build();

        NotificationManager nm =
                (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(nextNotifyId(), n);
    }

    private JSONObject makeRequest(String filterDate) throws Exception {
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                "http://192.168.137.131/get_new_phones.php?date=" + filterDate.replace(" ", "%20"),
                future, future);
        queue.add(request);
        return future.get(10, TimeUnit.SECONDS);
    }
}
