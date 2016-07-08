package dpiki.notificator;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import dpiki.notificator.data.MarketClient;

/**
 * Created by prog1 on 07.07.2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<MarketClient> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNameClient;
        public TextView tvUnreadNotificationCount;
        public TextView tvFilter;

        public ViewHolder(View v) {
            super(v);
            tvNameClient = (TextView) v.findViewById(R.id.tv_recycler_item_name_client);
            tvUnreadNotificationCount = (TextView) v.findViewById(R.id.tv_recycler_item_unread_notification_count);
            tvFilter = (TextView) v.findViewById(R.id.tv_recycler_item_filter);
        }
    }

    public RecyclerAdapter(ArrayList<MarketClient> dataset) {
        mDataset = dataset;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvNameClient.setText(mDataset.get(position).getName());
        holder.tvUnreadNotificationCount.setText(mDataset.get(position).getUnreadNotificationCount());
        String filter =
                mDataset.get(position).getPref1() + "\n" +
                mDataset.get(position).getPref2() + "\n" +
                mDataset.get(position).getPref3();
        holder.tvFilter.setText(filter);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}

