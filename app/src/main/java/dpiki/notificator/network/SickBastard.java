package dpiki.notificator.network;

import java.util.ArrayList;
import java.util.List;

import dpiki.notificator.DatabaseUtils;
import dpiki.notificator.PrefManager;
import dpiki.notificator.data.Recommendation;
import dpiki.notificator.network.dataobjects.RealEstate;
import dpiki.notificator.network.dataobjects.Requisition;

/**
 * Created by Lenovo on 11.08.2016.
 */
public class SickBastard {
    private final List<Requisition> requisitions = new ArrayList<>();
    //private final List<RealEstate> realEstates = new ArrayList<>();
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
        return retVal;
    }

    public List<Requisition> getRequisitions() {
        if (isRequsitionsValid) {
            return requisitions;
        } else {
            return null;
        }
    }

    public List<Recommendation> getRecommendationsForRequisition(long id, String type) {
        return null;
    }

    public void refresh() {

    }

    public void clearUnreadRecommendations(long id, String type) {

    }
}
