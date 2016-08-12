package dpiki.notificator.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dpiki.notificator.DatabaseUtils;
import dpiki.notificator.PrefManager;
import dpiki.notificator.data.Recommendation;
import dpiki.notificator.data.RealEstate;
import dpiki.notificator.data.Requisition;

/**
 * Created by Lenovo on 11.08.2016.
 */
public class SickBastard {
    private final List<Requisition> requisitions = new ArrayList<>();
    private boolean isRequisitionsValid = false;

    private DatabaseUtils mDbUtils;
    private ServerApiWrapper mWrapper;
    private PrefManager mPrefManager;

    public SickBastard(DatabaseUtils mDbUtils, ServerApiWrapper mWrapper, PrefManager mPrefManager) {
        this.mDbUtils = mDbUtils;
        this.mWrapper = mWrapper;
        this.mPrefManager = mPrefManager;
    }

    public List<Recommendation> getRecommendations() {
        List<Recommendation> retVal = new ArrayList<>();

        if (!isRequisitionsValid) {
            refresh();
        }

        if (isRequisitionsValid) {
            try {
                String strLastDate = mPrefManager.getLastFetchDate();
                mPrefManager.putLastFetchDate(strLastDate);

                List<RealEstate> realEstates = mWrapper.getRealEstates(strLastDate);

                synchronized (requisitions) {
                    for (RealEstate i : realEstates) {
                        for (Requisition j : requisitions) {
                            if (i.isMatch(j)) {
                                retVal.add(new Recommendation(j.id, i.id, i.type));
                                j.unreadRecommendationsCount++;
                            }
                        }

                        if (i.updatedAt.compareTo(strLastDate) > 0)
                            strLastDate = i.updatedAt;
                    }

                    for (Requisition i : requisitions) {
                        mDbUtils.setUnreadRecommendationsCount(i.id, i.type, i.unreadRecommendationsCount);
                    }
                }

                mDbUtils.addRecommendations(retVal);
                mPrefManager.putLastFetchDate(strLastDate);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return retVal;
    }

    public List<Requisition> getRequisitions() {
        if (isRequisitionsValid) {
            synchronized (requisitions) {
                return requisitions;
            }
        } else {
            return null;
        }
    }

    public List<Long> getRecommendationsForRequisition(long id, String type) {
        return mDbUtils.readRecommendations(id, type);
    }

    public void refresh() {
        isRequisitionsValid = false;

        try {
            List<Requisition> response = mWrapper.getRequisitions(0L);
            synchronized (requisitions) {
                requisitions.clear();
                requisitions.addAll(response);
                for (Requisition i : requisitions) {
                    i.unreadRecommendationsCount = mDbUtils.getUnreadRecommendationsCount(i.id, i.type);
                }
            }
            mDbUtils.clearRecommendations(requisitions);
            isRequisitionsValid = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearUnreadRecommendations(long id, String type) {
        synchronized (requisitions) {
            for (Requisition i : requisitions) {
                if (i.id.equals(id) && i.type.equals(type)) {
                    i.unreadRecommendationsCount = 0;
                    break;
                }
            }
        }
        mDbUtils.setUnreadRecommendationsCount(id, type, 0);
    }

}
