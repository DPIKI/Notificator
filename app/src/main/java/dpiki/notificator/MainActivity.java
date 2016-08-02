package dpiki.notificator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import dpiki.notificator.network.SyncMarketService;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewById(R.id.activity_main_rv)
    protected RecyclerView recyclerView;
    @ViewById(R.id.toolbar)
    protected Toolbar toolbar;
    @ViewById(R.id.toolbar_tv_title)
    protected TextView tvToolbarTitle;

    private RecyclerAdapter recyclerAdapter;
    private BroadcastReceiver broadcastReceiver;
    private Context contextActivity = this;

    @AfterViews
    protected void initRecyclerView() {
        List<Client> clients = DatabaseHelper.readClients(this);
        recyclerAdapter = new RecyclerAdapter(clients, new ItemClickListener());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerAdapter);
    }

    @AfterViews
    protected void initToolbar() {
        setSupportActionBar(toolbar);
    }

    @AfterViews
    protected void initService() {
        SyncMarketService.startNotificationService(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        broadcastReceiver = new Receiver();

        IntentFilter intentFilter = new IntentFilter(SyncMarketService.ACTION_NEW_RECOMMENDATIONS);
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

    public class ItemClickListener implements OnViewClickListener {
        @Override
        public void onViewClicked(Client client, int position) {
            if (client == null)
                return;

            if (client.notifCount == 0)
                return;

            DatabaseHelper.clearNotifications(contextActivity, client.id, client.type);
            recyclerAdapter.clearUnreadNotifications(position);
        }
    }

    public class Receiver extends android.content.BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(SyncMarketService.ACTION_START_RECEIVE)) {
                Toast.makeText(contextActivity, "Receiver:Started...", Toast.LENGTH_LONG).show();
            } else if (intent.getAction().equals(SyncMarketService.ACTION_STOP_RECEIVE)) {
                Toast.makeText(contextActivity, "Receiver:Stopped...", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(contextActivity, "Receiver:Update...", Toast.LENGTH_LONG).show();
                recyclerAdapter.update(DatabaseHelper.readClients(contextActivity));
            }
        }
    }
}
