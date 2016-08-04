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
import dpiki.notificator.data.Requirement;

/**
 * Created by prog1 on 07.07.2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public List<Requirement> mDataset;
    private OnViewClickListener mItemClickListener;

    public RecyclerAdapter(List<Requirement> dataset,
                           OnViewClickListener listener) {
        mItemClickListener = listener;
        update(dataset);
    }

    public void update(List<Requirement> marketClients) {
        mDataset = marketClients;
        Collections.reverse(mDataset);
        this.notifyDataSetChanged();
    }

    public void clearUnreadNotifications(int position) {
        int newPos = position;
        Iterator<Requirement> i = this.mDataset.listIterator(position);
        while (i.hasNext()) {
            Requirement curr = i.next();
            if (curr.unreadRecommendations == 0)
                break;
            newPos++;
        }

        newPos--;
        newPos = newPos > position ? newPos : position;

        Requirement curr = this.mDataset.get(position);
        curr.unreadRecommendations = 0;
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
        Requirement requirement = mDataset.get(position);

        holder.tvNameClient.setText("idRequirements = " + requirement.id);
        holder.tvNotifCount.setText(requirement.unreadRecommendations.toString());
        holder.tvNotifCount.setVisibility(requirement.unreadRecommendations == 0 ? View.INVISIBLE : View.VISIBLE);
        holder.rlCircle.setVisibility(requirement.unreadRecommendations == 0 ? View.INVISIBLE : View.VISIBLE);
        holder.tvType.setText(requirement.type);

        holder.requirement = requirement;
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
        public Requirement requirement;

        OnViewClickListener listener;

        public ViewHolder(View v, OnViewClickListener listener) {
            super(v);
            tvNameClient = (TextView) v.findViewById(R.id.recycler_item_tv_name_client);
            tvNotifCount = (TextView) v.findViewById(R.id.recycler_item_tv_notif_count);
            tvType = (TextView) v.findViewById(R.id.recycler_item_tv_type);
            relativeLayout = (RelativeLayout) v.findViewById(R.id.recycler_item_rl_general);
            rlCircle = (RelativeLayout) v.findViewById(R.id.recycler_item_rl_circle);

            requirement = null;
            relativeLayout.setOnClickListener(this);
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            listener.onViewClicked(requirement, getAdapterPosition());
        }
    }
}

