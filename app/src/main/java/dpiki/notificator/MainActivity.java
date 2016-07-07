package dpiki.notificator;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import dpiki.notificator.data.MarketClient;
import dpiki.notificator.receivers.BootReceiver;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.et_ip_address);

        BootReceiver.initAlarmManager(this);

        ArrayList<MarketClient> clients = DatabaseHelper.readClients(this);
        for (MarketClient client : clients) {
            Log.d(TAG,
                    client.getId().toString() + " " +
                    client.getName() + " " +
                    client.getPref1() + " " +
                    client.getPref2() + " " +
                    client.getPref3()
            );
        }

        int nclients = DatabaseHelper.getNumberNotifyClients(this);
        int nphones = DatabaseHelper.getNumberNotifyPhones(this);
        Log.d(TAG,nclients + " клииентов " + nphones +  " телефонов");

    }

    public void onChangeIpClick(View v) {
        String s = editText.getText().toString();
        String[] sl = s.split(".");

        if (sl.length != 4)
            return;

        for (String i : sl) {
            try {
                int k = Integer.parseInt(i);

                if (k > 255 || k < 0)
                    return;

            } catch (Exception e) {
                return;
            }
        }

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(SyncMarketService.PREF_KEY_IP, s);
        editor.apply();
    }
}
