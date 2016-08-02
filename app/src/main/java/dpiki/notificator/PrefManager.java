package dpiki.notificator;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Lenovo on 02.08.2016.
 */
public class PrefManager {
    private static String PREF_NAME = "NotificatorPreferences";
    private static String PREF_KEY_LAST_FETCH_DATE = "LastFetchDate";
    public static final String PREF_KEY_RECEIVE_NOTIFICATIONS = "ReceiveNotifications";

    private Context mContext;

    public PrefManager(Context context) {
        this.mContext = context;
    }

    public String getLastFetchDate(String realtyName) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date currDate = new Date();
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, 0);
        return pref.getString(PREF_KEY_LAST_FETCH_DATE + realtyName,
                sdf.format(currDate));
    }

    public void putLastFetchDate(String realtyName, String lastFetchDate) {
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_KEY_LAST_FETCH_DATE + realtyName, lastFetchDate);
        editor.apply();
    }

    public Boolean getReceiveNotificationsFlag() {
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, 0);
        return pref.getBoolean(PREF_KEY_RECEIVE_NOTIFICATIONS, true);
    }

    public void putReceiveNotificationsFlag(Boolean flag) {
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(PREF_KEY_RECEIVE_NOTIFICATIONS, flag);
        editor.apply();
    }
}
