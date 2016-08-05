package dpiki.notificator.network;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dpiki.notificator.DatabaseUtils;
import dpiki.notificator.PrefManager;
import dpiki.notificator.data.Realty;
import dpiki.notificator.data.Recommendation;
import dpiki.notificator.data.Requirement;
import dpiki.notificator.network.dataobjects.RealtyBase;
import dpiki.notificator.network.dataobjects.RequirementBase;

/**
 * Created by Lenovo on 07.07.2016.
 */
public class DataFetcher {
    public PrefManager mPrefManager;
    public DatabaseUtils mDbUtils;

    public DataFetcher(PrefManager mPrefManager,
                       DatabaseUtils mDbUtils) {
        this.mPrefManager = mPrefManager;
        this.mDbUtils = mDbUtils;
    }

    public List<Recommendation> fetch(DataFetcherAdapter adapter) {
        List<Recommendation> retVal = new ArrayList<>();

        String strLastDate = mPrefManager.getLastFetchDate(adapter.getType());
        mPrefManager.putLastFetchDate(adapter.getType(), strLastDate);

        List<RequirementBase> tRequirements = adapter.getRequirements(0); // TODO: replace with real agentId
        if (tRequirements == null || tRequirements.isEmpty())
            return retVal;
        List<Requirement> requirements = new ArrayList<>();
        for (RequirementBase i : tRequirements) {
            requirements.add(mapRequirement(i, adapter.getType()));
        }
        mDbUtils.updateRequirements(requirements, adapter.getType());

        List<RealtyBase> realty = adapter.getRealty(strLastDate);
        if (realty == null || realty.isEmpty())
            return retVal;
        RealtyBase lastRealty = Collections.max(realty, new Comparator<RealtyBase>() {
            @Override
            public int compare(RealtyBase lhs, RealtyBase rhs) {
                return lhs.createdAt.compareTo(rhs.createdAt);
            }
        });
        mPrefManager.putLastFetchDate(adapter.getType(), lastRealty.createdAt);

        for (RequirementBase i : tRequirements) {
            for (RealtyBase j : realty) {
                if (j.isMatch(i)) {
                    Requirement req = mapRequirement(i, adapter.getType());
                    Realty realEstate = mapRealty(j, adapter.getType());
                    retVal.add(new Recommendation(req, realEstate));
                }
            }
        }

        return retVal;
    }

    private Requirement mapRequirement(RequirementBase req, String type) {
        Requirement retVal = new Requirement();
        retVal.id = req.idRequirements;
        retVal.type = type;
        retVal.unreadRecommendations = mDbUtils.getUnreadRecommendationsCount(req.idRequirements, type);
        return retVal;
    }

    private Realty mapRealty(RealtyBase rb, String type) {
        Realty retVal = new Realty();
        retVal.id = rb.id;
        retVal.type = type;
        return retVal;
    }

}
