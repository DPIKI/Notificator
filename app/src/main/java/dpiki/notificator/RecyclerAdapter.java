package dpiki.notificator;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import dpiki.notificator.data.MarketClient;

/**
 * Created by prog1 on 07.07.2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public ArrayList<MarketClient> mDataset;
    private AdapterView.OnItemClickListener mItemClickListener;

    public RecyclerAdapter(ArrayList<MarketClient> dataset,
                           AdapterView.OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
        update(dataset);
    }

    public void update(ArrayList<MarketClient> marketClients) {
        mDataset = marketClients;
        Collections.reverse(mDataset);
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        return new ViewHolder(v, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvNameClient.setText(mDataset.get(position).getName());
        holder.tvUnreadNotificationCount.setText("" + mDataset.get(position).getUnreadNotificationCount());
        holder.tvUnreadNotificationCount.setVisibility(mDataset
                .get(position)
                .getUnreadNotificationCount() == 0 ?
                View.INVISIBLE :
                View.VISIBLE);
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

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public TextView tvNameClient;
        public TextView tvUnreadNotificationCount;
        public TextView tvFilter;
        public CardView cardView;

        AdapterView.OnItemClickListener listener;

        public ViewHolder(View v, AdapterView.OnItemClickListener listener) {
            super(v);
            this.listener = listener;
            tvNameClient = (TextView) v.findViewById(R.id.tv_recycler_item_name_client);
            tvUnreadNotificationCount = (TextView) v.findViewById(R.id.tv_recycler_item_unread_notification_count);
            tvFilter = (TextView) v.findViewById(R.id.tv_recycler_item_filter);
            cardView = (CardView) v;
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(null, v, getAdapterPosition(), 0);
        }
    }
}

