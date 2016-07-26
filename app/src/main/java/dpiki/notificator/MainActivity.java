package dpiki.notificator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
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

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import dpiki.notificator.data.ClientResponse;
import dpiki.notificator.network.ServerApi;
import dpiki.notificator.network.SyncMarketService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    private RecyclerAdapter recyclerAdapter;
    private BroadcastReceiver broadcastReceiver;
    private Context contextActivity = this;

    @ViewById(R.id.switch_settings) protected Switch sw;
    @ViewById(R.id.activity_main_recycler_view) protected RecyclerView recyclerView;
    @ViewById(R.id.toolbar) protected Toolbar toolbar;
    @ViewById(R.id.tv_toolbar_title) protected TextView tvToolbarTitle;

    @AfterViews
    protected void initRecyclerView() {
        DatabaseHelper.fillTestData(this);
        ArrayList<MarketClient> clients = DatabaseHelper.readClients(this);
        recyclerAdapter = new RecyclerAdapter(clients, new ItemClickListener());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerAdapter);
    }

    @AfterViews
    protected void initToolbar() {
        tvToolbarTitle.setText(getString(R.string.app_name));
        setSupportActionBar(toolbar);
    }

    @AfterViews
    protected void initService() {
        SyncMarketService.configureService(this, new MyFetcherCreator());
        SyncMarketService.rerunNotificationService(this);
    }

    @CheckedChange(R.id.switch_settings)
    protected void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            SyncMarketService.startNotificationService(contextActivity);
        } else {
            SyncMarketService.stopNotificationService(contextActivity);
        }
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
