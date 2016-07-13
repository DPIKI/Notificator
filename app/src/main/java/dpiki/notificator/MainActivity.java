package dpiki.notificator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dpiki.notificator.data.MarketClient;
import dpiki.notificator.network.MyFetcher;
import dpiki.notificator.network.SyncMarketService;

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
            DatabaseHelper.clearUnreadNotification(client.getId(), contextActivity);
            recyclerAdapter.update(DatabaseHelper.readClients(contextActivity));
        }
    }

    public class Receiver extends android.content.BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(contextActivity, "Update...", Toast.LENGTH_LONG).show();
            recyclerAdapter.update(DatabaseHelper.readClients(contextActivity));
        }
    }
}
