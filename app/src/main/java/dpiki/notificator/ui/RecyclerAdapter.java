package dpiki.notificator.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import dpiki.notificator.R;

/**
 * Created by prog1 on 07.07.2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public List<IView.RequisitionInfoContainer> mDataSet;
    private OnViewClickListener mItemClickListener;

    public RecyclerAdapter (OnViewClickListener listener) {
        mItemClickListener = listener;
    }

    public void update(List<IView.RequisitionInfoContainer> marketClients) {
        mDataSet = marketClients;
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
        if (mDataSet != null) {
            IView.RequisitionInfoContainer r = mDataSet.get(position);
            holder.tvNameClient.setText(r.fio);
            holder.tvNotifCount.setText("" + r.unread);
            holder.tvNotifCount.setVisibility(r.unread == 0 ? View.INVISIBLE : View.VISIBLE);
            holder.rlCircle.setVisibility(r.unread == 0 ? View.INVISIBLE : View.VISIBLE);
            holder.tvType.setText(r.type);
            holder.requisition = r;
        }
    }

    @Override
    public int getItemCount() {
        if (mDataSet != null) {
            return mDataSet.size();
        } else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public TextView tvNameClient;
        public TextView tvNotifCount;
        public TextView tvType;
        public RelativeLayout rlCircle;
        public RelativeLayout relativeLayout;
        public IView.RequisitionInfoContainer requisition;

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
        void onViewClicked(IView.RequisitionInfoContainer r, int position);
    }
}

