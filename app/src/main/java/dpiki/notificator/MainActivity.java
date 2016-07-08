package dpiki.notificator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import dpiki.notificator.data.MarketClient;
import dpiki.notificator.network.BootReceiver;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager rvLayoutManager;
    private RecyclerAdapter recyclerAdapter;

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.et_ip_address);
        recyclerView = (RecyclerView) findViewById(R.id.activity_main_recycler_view) ;
        recyclerView.setHasFixedSize(true);

        BootReceiver.initAlarmManager(this);

        ArrayList<MarketClient> clients = DatabaseHelper.readClients(this);
        recyclerAdapter = new RecyclerAdapter(clients);

        recyclerView.setAdapter(recyclerAdapter);

        for (MarketClient client : clients) {
            Log.d(TAG,
                    client.getId().toString() + " " +
                    client.getName() + " " +
                    client.getPref1() + " " +
                    client.getPref2() + " " +
                    client.getPref3()
            );
        }

    }

    public void onChangeIpClick(View v) {
    }
}
