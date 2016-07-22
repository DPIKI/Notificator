package dpiki.notificator;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import dpiki.notificator.data.MarketClient;

/**
 * Created by prog1 on 07.07.2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public ArrayList<MarketClient> mDataset;
    private OnCardViewClickListener mItemClickListener;

    public RecyclerAdapter(ArrayList<MarketClient> dataset,
                           OnCardViewClickListener listener) {
        mItemClickListener = listener;
        update(dataset);
    }

    public void update(ArrayList<MarketClient> marketClients) {
        mDataset = marketClients;
        Collections.reverse(mDataset);
        this.notifyDataSetChanged();
    }

    public void clearUnreadNotifications(int position) {
        int newPos = position;
        Iterator<MarketClient> i = this.mDataset.listIterator(position);
        while (i.hasNext()) {
            MarketClient curr = i.next();
            if (curr.getUnreadNotificationCount() == 0)
                break;
            newPos++;
        }

        newPos--;
        newPos = newPos > position ? newPos : position;

        MarketClient curr = this.mDataset.get(position);
        curr.setUnreadNotificationCount(0);
        this.notifyItemChanged(position);

        this.mDataset.remove(position);
        this.mDataset.add(newPos, curr);
        this.notifyItemMoved(position, newPos);
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
        MarketClient client = mDataset.get(position);

        holder.tvNameClient.setText(client.getName());
        holder.tvUnreadNotificationCount.setText(client.getUnreadNotificationCount().toString());
        holder.tvUnreadNotificationCount.setVisibility(client
                .getUnreadNotificationCount() == 0 ?
                View.INVISIBLE :
                View.VISIBLE);
        String filter =
                client.getPref1() + "\n" +
                client.getPref2() + "\n" +
                client.getPref3();
        holder.tvFilter.setText(filter);

        holder.currClient = client;
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
        public MarketClient currClient;

        OnCardViewClickListener listener;

        public ViewHolder(View v, OnCardViewClickListener listener) {
            super(v);

            tvNameClient = (TextView) v.findViewById(R.id.tv_recycler_item_name_client);
            tvUnreadNotificationCount = (TextView) v.findViewById(R.id.tv_recycler_item_unread_notification_count);
            tvFilter = (TextView) v.findViewById(R.id.tv_recycler_item_filter);
            cardView = (CardView) v.findViewById(R.id.card_view);
            currClient = null;

            cardView.setOnClickListener(this);
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            listener.onCardViewClicked(currClient, getAdapterPosition());
        }
    }
}

