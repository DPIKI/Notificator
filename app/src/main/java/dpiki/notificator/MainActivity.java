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

import javax.inject.Inject;

import dpiki.notificator.data.Requirement;
import dpiki.notificator.network.SyncMarketService;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @Inject
    public DatabaseUtils mDatabaseUtils;

    @ViewById(R.id.activity_main_rv)
    protected RecyclerView recyclerView;
    @ViewById(R.id.toolbar)
    protected Toolbar toolbar;
    @ViewById(R.id.toolbar_tv_title)
    protected TextView tvToolbarTitle;

    private RecyclerAdapter recyclerAdapter;
    private BroadcastReceiver broadcastReceiver;

    @AfterViews
    protected void initRecyclerView() {
        App.getInstance().inject(this);
        List<Requirement> requirements = mDatabaseUtils.readRequirements();
        recyclerAdapter = new RecyclerAdapter(requirements, new ItemClickListener());
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

        recyclerAdapter.update(mDatabaseUtils.readRequirements());
    }

    @Override
    public void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }

    public class ItemClickListener implements OnViewClickListener {
        @Override
        public void onViewClicked(Requirement requirement, int position) {
            if (requirement == null)
                return;

            if (requirement.unreadRecommendations == 0)
                return;

            mDatabaseUtils.clearUnreadRecommendationsCount(requirement.id, requirement.type);
            recyclerAdapter.clearUnreadNotifications(position);
        }
    }

    public class Receiver extends android.content.BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(SyncMarketService.ACTION_START_RECEIVE)) {
                Toast.makeText(MainActivity.this, "Receiver:Started...", Toast.LENGTH_LONG).show();
            } else if (intent.getAction().equals(SyncMarketService.ACTION_STOP_RECEIVE)) {
                Toast.makeText(MainActivity.this, "Receiver:Stopped...", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "Receiver:Update...", Toast.LENGTH_LONG).show();
                recyclerAdapter.update(mDatabaseUtils.readRequirements());
            }
        }
    }
}
