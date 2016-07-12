package dpiki.notificator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import dpiki.notificator.data.MarketClient;
import dpiki.notificator.network.BootReceiver;
import dpiki.notificator.network.MyFetcher;
import dpiki.notificator.network.SyncMarketService;

public class MainActivity extends AppCompatActivity {
    private RecyclerAdapter recyclerAdapter;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, SyncMarketService.class);
        startService(intent);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.activity_main_recycler_view);
        assert recyclerView != null;
        recyclerView.setHasFixedSize(true);

        ArrayList<MarketClient> clients = DatabaseHelper.readClients(this);
        ItemClickListener listener = new ItemClickListener();
        recyclerAdapter = new RecyclerAdapter(clients, listener);

        RecyclerView.LayoutManager rvLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rvLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();

        broadcastReceiver = new Receiver();
        IntentFilter intentFilter = new IntentFilter(MyFetcher.ACTION_NEW_RECOMMENDATIONS);
        registerReceiver(broadcastReceiver, intentFilter);
        recyclerAdapter.update(DatabaseHelper.readClients(MainActivity.this));
    }

    @Override
    public void onStop() {
        super.onStop();

        unregisterReceiver(broadcastReceiver);
    }

    public class ItemClickListener implements OnCardViewClickListener {
        @Override
        public void onCardViewClicked(MarketClient client, int position) {
            if (client == null)
                return;
            DatabaseHelper.clearUnreadNotification(client.getId(), MainActivity.this);
            recyclerAdapter.update(DatabaseHelper.readClients(MainActivity.this));
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
