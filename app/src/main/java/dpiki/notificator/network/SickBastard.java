package dpiki.notificator.network;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dpiki.notificator.DatabaseUtils;
import dpiki.notificator.PrefManager;
import dpiki.notificator.data.Recommendation;
import dpiki.notificator.data.Requirement;
import dpiki.notificator.network.dataobjects.RealEstate;
import dpiki.notificator.network.dataobjects.Requisition;

/**
 * Created by Lenovo on 11.08.2016.
 */
public class SickBastard {
    private final List<Requisition> requisitions = new ArrayList<>();
    private boolean isRequsitionsValid = false;

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

        if (!isRequsitionsValid) {
            refresh();
        }

        if (isRequsitionsValid) {
            try {
                requisitions.clear();

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

    public List<Requisition> getRequisitions() {
        if (isRequsitionsValid) {
            return requisitions;
        } else {
            return null;
        }
    }

    public List<Long> getRecommendationsForRequisition(long id, String type) {
        return mDbUtils.readRecommendations(id, type);
    }

    public void refresh() {
        isRequsitionsValid = false;

        try {
            requisitions = mWrapper.getRequisitions(0);
            mDbUtils.clearRecommendations(requisitions);
            for (Requisition i : requisitions) {
                i.unreadRecommendationsCount = mDbUtils.getUnreadRecommendationsCount(i.id, i.type);
            }
            isRequsitionsValid = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearUnreadRecommendations(long id, String type) {
        for (Requisition i : requisitions) {
            if (i.id.equals(id) && i.type.equals(type)) {
                i.unreadRecommendationsCount = 0;
                break;
            }
        }
        mDbUtils.setUnreadRecommendationsCount(id, type, 0);
    }
}
