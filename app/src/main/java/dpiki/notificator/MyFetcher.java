package dpiki.notificator;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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

    MyFetcher(Context context) {
        this.context = context;
        queue = Volley.newRequestQueue(context);
    }

    @Override
    public ArrayList<Phone> loadItems() throws Exception {
        Date currentDate = new Date();
        String strCurrentDate = sdf.format(currentDate);
        SharedPreferences pref = context.getSharedPreferences("MyFetcher", 0);
        String strLastFetch = pref.getString(PREF_KEY_LAST_DATE, strCurrentDate);

        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_KEY_LAST_DATE, strLastFetch);
        editor.apply();

        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                "192.168.137.110/get_new_phones.php?date=" + strLastFetch,
                future, future);
        queue.add(request);
        JSONObject response = future.get(10, TimeUnit.SECONDS);
        extractResponse(response);
    }

    @Override
    public ArrayList<MarketClient> loadFilters() throws Exception {
        return null;
    }

    @Override
    public Boolean isMatch(Phone i, MarketClient f) {
        return null;
    }

    @Override
    public void onNewRecommendations(ArrayList<Recommendation> recommendations) throws Exception{

    }

    ArrayList<Phone> extractResponse(JSONObject response) throws Exception {
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
}
