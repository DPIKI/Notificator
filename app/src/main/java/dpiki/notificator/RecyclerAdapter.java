package dpiki.notificator;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import dpiki.notificator.data.Client;

/**
 * Created by prog1 on 07.07.2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public ArrayList<Client> mDataset;
    private OnCardViewClickListener mItemClickListener;

    public RecyclerAdapter(ArrayList<Client> dataset,
                           OnCardViewClickListener listener) {
        mItemClickListener = listener;
        update(dataset);
    }

    public void update(ArrayList<Client> marketClients) {
        mDataset = marketClients;
        Collections.reverse(mDataset);
        this.notifyDataSetChanged();
    }

    public void clearUnreadNotifications(int position) {
        int newPos = position;
        Iterator<Client> i = this.mDataset.listIterator(position);
        while (i.hasNext()) {
            Client curr = i.next();
            if (curr.notifCount == 0)
                break;
            newPos++;
        }

        newPos--;
        newPos = newPos > position ? newPos : position;

        Client curr = this.mDataset.get(position);
        curr.notifCount = 0;
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
        Client client = mDataset.get(position);

        holder.tvNameClient.setText(client.fio);
        holder.tvNotifCount.setText(client.notifCount.toString());
        holder.tvNotifCount.setVisibility(client.notifCount == 0 ? View.INVISIBLE : View.VISIBLE);
        holder.rlCircle.setVisibility(client.notifCount == 0 ? View.INVISIBLE : View.VISIBLE);
        holder.tvType.setText(client.type);

        holder.currClient = client;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public TextView tvNameClient;
        public TextView tvNotifCount;
        public TextView tvType;
        public RelativeLayout rlCircle;
        public RelativeLayout relativeLayout;
        public Client currClient;

        OnCardViewClickListener listener;

        public ViewHolder(View v, OnCardViewClickListener listener) {
            super(v);
            tvNameClient = (TextView) v.findViewById(R.id.recycler_item_tv_name_client);
            tvNotifCount = (TextView) v.findViewById(R.id.recycler_item_tv_notif_count);
            tvType = (TextView) v.findViewById(R.id.recycler_item_tv_type);
            relativeLayout = (RelativeLayout) v.findViewById(R.id.recycler_item_rl_general);
            rlCircle = (RelativeLayout) v.findViewById(R.id.recycler_item_rl_circle);

            currClient = null;
            relativeLayout.setOnClickListener(this);
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            listener.onCardViewClicked(currClient, getAdapterPosition());
        }
    }
}

