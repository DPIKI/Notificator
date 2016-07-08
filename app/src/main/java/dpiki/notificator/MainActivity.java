package dpiki.notificator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
<<<<<<< HEAD
import android.view.View;
import android.widget.AdapterView;
=======
import android.util.Log;
>>>>>>> 732af539b614d8ccff98b8bb88e882f2177766bb
import android.widget.Toast;

import java.util.ArrayList;

import dpiki.notificator.data.MarketClient;
import dpiki.notificator.network.BootReceiver;
import dpiki.notificator.network.MyFetcher;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager rvLayoutManager;
    private RecyclerAdapter recyclerAdapter;

    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.activity_main_recycler_view) ;
        recyclerView.setHasFixedSize(true);

        BootReceiver.initAlarmManager(this);

        ArrayList<MarketClient> clients = DatabaseHelper.readClients(this);
<<<<<<< HEAD
        ItemClickListener listener = new ItemClickListener();
        recyclerAdapter = new RecyclerAdapter(clients, listener);
=======
        for (MarketClient client : clients) {
            Log.d(TAG,
                    client.getId().toString() + " " +
                    client.getName() + " " +
                    client.getPref1() + " " +
                    client.getPref2() + " " +
                    client.getPref3()
            );
        }

        recyclerAdapter = new RecyclerAdapter(clients);
>>>>>>> 732af539b614d8ccff98b8bb88e882f2177766bb

        rvLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rvLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();

        broadcastReceiver = new Receiver();
        IntentFilter intentFilter = new IntentFilter(MyFetcher.ACTION_NEW_RECOMMENDATIONS);
<<<<<<< HEAD
        registerReceiver(broadcastReceiver,intentFilter);

=======
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void onStop() {
        super.onStop();

        unregisterReceiver(broadcastReceiver);
>>>>>>> 732af539b614d8ccff98b8bb88e882f2177766bb
    }

    public class ItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            DatabaseHelper.clearUnreadNotification(recyclerAdapter.mDataset.get(position).getId(), this);
        }
    }

    public class Receiver extends android.content.BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(MainActivity.this, "Update...", Toast.LENGTH_LONG).show();
            recyclerAdapter.update(DatabaseHelper.readClients(MainActivity.this));
        }
    }
}
