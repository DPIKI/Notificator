package dpiki.notificator.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import dpiki.notificator.R;
import dpiki.notificator.data.Requisition;

/**
 * Created by prog1 on 07.07.2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public List<Requisition> mDataset;
    private OnViewClickListener mItemClickListener;

    public RecyclerAdapter(List<Requisition> dataset,
                           OnViewClickListener listener) {
        mItemClickListener = listener;
        update(dataset);
    }

    public void update(List<Requisition> marketClients) {
        mDataset = marketClients;
        Collections.reverse(mDataset);
        this.notifyDataSetChanged();
    }

    public void clearUnreadNotifications(int position) {
        int newPos = position;
        Iterator<Requisition> i = this.mDataset.listIterator(position);
        while (i.hasNext()) {
            Requisition curr = i.next();
            if (curr.unreadRecommendationsCount == 0)
                break;
            newPos++;
        }

        newPos--;
        newPos = newPos > position ? newPos : position;

        Requisition curr = this.mDataset.get(position);
        curr.unreadRecommendationsCount = 0;
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
        Requisition requisition = mDataset.get(position);

        holder.tvNameClient.setText("idRequisitions = " + requisition.id);
        holder.tvNotifCount.setText(requisition.unreadRecommendationsCount.toString());
        holder.tvNotifCount.setVisibility(requisition.unreadRecommendationsCount == 0 ? View.INVISIBLE : View.VISIBLE);
        holder.rlCircle.setVisibility(requisition.unreadRecommendationsCount == 0 ? View.INVISIBLE : View.VISIBLE);
        holder.tvType.setText(requisition.type);

        holder.requisition = requisition;
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
        public Requisition requisition;

        OnViewClickListener listener;

        public ViewHolder(View v, OnViewClickListener listener) {
            super(v);
            tvNameClient = (TextView) v.findViewById(R.id.recycler_item_tv_name_client);
            tvNotifCount = (TextView) v.findViewById(R.id.recycler_item_tv_notif_count);
            tvType = (TextView) v.findViewById(R.id.recycler_item_tv_type);
            relativeLayout = (RelativeLayout) v.findViewById(R.id.recycler_item_rl_general);
            rlCircle = (RelativeLayout) v.findViewById(R.id.recycler_item_rl_circle);

            requisition = null;
            relativeLayout.setOnClickListener(this);
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            listener.onViewClicked(requisition, getAdapterPosition());
        }
    }

    /**
     * Created by User on 09.07.2016.
     */
    public interface OnViewClickListener {
        void onViewClicked(Requisition requisition, int position);
    }
}

