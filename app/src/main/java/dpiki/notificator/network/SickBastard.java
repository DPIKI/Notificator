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
//TODO: Прикрутить синхронизацию
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

    synchronized public List<Recommendation> getRecommendations() {
        List<Recommendation> retVal = new ArrayList<>();

        if (!isRequisitionsValid) {
            refresh();
        }

        if (isRequisitionsValid) {
            try {
                String strLastDate = mPrefManager.getLastFetchDate();
                mPrefManager.putLastFetchDate(strLastDate);

                List<RealEstate> realEstates = mWrapper.getRealEstates(strLastDate);

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

                mDbUtils.addRecommendations(retVal);
                for (Requisition i : requisitions) {
                    mDbUtils.setUnreadRecommendationsCount(i.id, i.type, i.unreadRecommendationsCount);
                }

                mPrefManager.putLastFetchDate(strLastDate);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return retVal;
    }

    synchronized public List<Requisition> getRequisitions() {
        if (isRequisitionsValid) {
            return requisitions;
        } else {
            return null;
        }
    }

    synchronized public List<Long> getRecommendationsForRequisition(long id, String type) {
        return mDbUtils.readRecommendations(id, type);
    }

    synchronized public void refresh() {
        isRequisitionsValid = false;

        try {
            requisitions.clear();
            requisitions.addAll(mWrapper.getRequisitions(0L));
            mDbUtils.clearRecommendations(requisitions);
            for (Requisition i : requisitions) {
                i.unreadRecommendationsCount = mDbUtils.getUnreadRecommendationsCount(i.id, i.type);
            }
            isRequisitionsValid = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    synchronized public void clearUnreadRecommendations(long id, String type) {
        for (Requisition i : requisitions) {
            if (i.id.equals(id) && i.type.equals(type)) {
                i.unreadRecommendationsCount = 0;
                break;
            }
        }
        mDbUtils.setUnreadRecommendationsCount(id, type, 0);
    }
}
