package dpiki.notificator.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import dpiki.notificator.App;
import dpiki.notificator.R;
import dpiki.notificator.network.SyncMarketService;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements IView {

    @ViewById(R.id.activity_main_rv)
    protected RecyclerView recyclerView;
    @ViewById(R.id.toolbar)
    protected Toolbar toolbar;
    @ViewById(R.id.toolbar_tv_title)
    protected TextView tvToolbarTitle;
    @ViewById(R.id.rl_activity_main_error_layout)
    protected RelativeLayout errorLayout;

    private RecyclerAdapter recyclerAdapter;
    private BroadcastReceiver broadcastReceiver;
    private IPresenter mPresenter;

    public MainActivity() {
    }

    @AfterViews
    protected void initRecyclerView() {
        recyclerAdapter = new RecyclerAdapter(new ItemClickListener());
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
    protected void initPresenter() {
        mPresenter = new PresenterImpl(this, App.getInstance().sickBastard());
        SyncMarketService.startNotificationService(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        broadcastReceiver = new Receiver();
        IntentFilter intentFilter = new IntentFilter(SyncMarketService.ACTION_NEW_RECOMMENDATIONS);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInvalidSync() {
        errorLayout.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showRequisitions(List<RequisitionInfoContainer> requisitions) {
        errorLayout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerAdapter.update(requisitions);
    }

    @Click(R.id.btn_activity_main_refresh)
    public void onClickRefreshButton(View v) {
        mPresenter.onRefreshButtonClicked();
    }

    public class ItemClickListener implements RecyclerAdapter.OnViewClickListener {
        @Override
        public void onViewClicked(RequisitionInfoContainer r, int position) {
            if (r == null)
                return;

            mPresenter.onItemClicked(r.id, r.type);
        }
    }

    public class Receiver extends android.content.BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(SyncMarketService.ACTION_NEW_RECOMMENDATIONS)) {
                mPresenter.onNewRecommendations();
            }
        }
    }
}
