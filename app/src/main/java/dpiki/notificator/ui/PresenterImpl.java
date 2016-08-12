package dpiki.notificator.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dpiki.notificator.data.Requisition;
import dpiki.notificator.network.SickBastard;

/**
 * Created by Lenovo on 12.08.2016.
 */
public class PresenterImpl implements IPresenter {
    private SickBastard mSickBastard;
    private IView mIView;

    public PresenterImpl(IView view, SickBastard sickBastard) {
        if (sickBastard == null || view == null) {
            throw new NullPointerException("Null pointer argument in constructor");
        }

        this.mSickBastard = sickBastard;
        this.mIView = view;
    }

    @Override
    public void onResume() {
        onRefreshButtonClicked();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onItemClicked(long id, String type) {

    }

    @Override
    public void onNewRecommendations() {
        onRefreshButtonClicked();
    }

    @Override
    public void onRefreshButtonClicked() {
        List<Requisition> requisitions = mSickBastard.getRequisitions();
        if (requisitions != null) {
            List<IView.RequisitionInfoContainer> containers = new ArrayList<>();
            for (Requisition i : requisitions) {
                containers.add(new IView.RequisitionInfoContainer(i.id, i.type, "FIO", i.unreadRecommendationsCount));
            }
            Collections.sort(containers, new ContainerComparator());
            mIView.showRequisitions(containers);
        } else {
            mIView.showInvalidSync();
        }
    }

    class ContainerComparator implements Comparator<IView.RequisitionInfoContainer> {
        @Override
        public int compare(IView.RequisitionInfoContainer lhs, IView.RequisitionInfoContainer rhs) {
            if (lhs.unread > rhs.unread) {
                return 1;
            } else if (lhs.unread < rhs.unread) {
                return -1;
            } else {
                return 0;
            }
        }
    }

}
