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
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dpiki.notificator.data.ClientResponse;
import dpiki.notificator.network.ServerApi;
import dpiki.notificator.network.SyncMarketService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private RecyclerAdapter recyclerAdapter;
    private BroadcastReceiver broadcastReceiver;
    private Switch sw;
    private Context contextActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        initToolbar();
        initSwitch();

        SyncMarketService.configureService(this, new MyFetcherCreator());
        SyncMarketService.rerunNotificationService(this);

        DatabaseHelper.fillTestData(this);
    }

    private void initSwitch() {
        sw = (Switch) findViewById(R.id.switch_settings);
        sw.setChecked(SyncMarketService.serverStatus(contextActivity));

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SyncMarketService.startNotificationService(contextActivity);
                } else {
                    SyncMarketService.stopNotificationService(contextActivity);
                }
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView tvToolbarTitle = (TextView) findViewById(R.id.tv_toolbar_title);
        tvToolbarTitle.setText(getString(R.string.app_name));
        setSupportActionBar(toolbar);
    }

    @Override
    public void onStart() {
        super.onStart();

        broadcastReceiver = new Receiver();
        IntentFilter intentFilter = new IntentFilter(MyFetcher.ACTION_NEW_RECOMMENDATIONS);
        registerReceiver(broadcastReceiver, intentFilter);
        intentFilter = new IntentFilter(SyncMarketService.ACTION_START_RECEIVE);
        registerReceiver(broadcastReceiver, intentFilter);
        intentFilter = new IntentFilter(SyncMarketService.ACTION_STOP_RECEIVE);
        registerReceiver(broadcastReceiver, intentFilter);
        recyclerAdapter.update(DatabaseHelper.readClients(contextActivity));
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

            if (client.getUnreadNotificationCount() == 0)
                return;

            DatabaseHelper.clearUnreadNotification(client.getId(), contextActivity);
            recyclerAdapter.clearUnreadNotifications(position);
        }
    }

    public class Receiver extends android.content.BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(SyncMarketService.ACTION_START_RECEIVE)) {
                Toast.makeText(contextActivity, "Started...", Toast.LENGTH_LONG).show();
            } else if (intent.getAction().equals(SyncMarketService.ACTION_STOP_RECEIVE)) {
                Toast.makeText(contextActivity, "Stopped...", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(contextActivity, "Update...", Toast.LENGTH_LONG).show();
                recyclerAdapter.update(DatabaseHelper.readClients(contextActivity));
            }
        }
    }
}
