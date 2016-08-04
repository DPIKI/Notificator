package dpiki.notificator.network;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dpiki.notificator.App;
import dpiki.notificator.DatabaseUtils;
import dpiki.notificator.PrefManager;
import dpiki.notificator.data.Realty;
import dpiki.notificator.data.RealtyTypes;
import dpiki.notificator.data.Requirement;
import dpiki.notificator.network.dataobjects.Households;
import dpiki.notificator.network.dataobjects.HouseholdsReq;

/**
 * Created by Lenovo on 02.08.2016.
 */
public class DataFetcherHousehold extends DataFetcher<Households, HouseholdsReq> {

    public DataFetcherHousehold(PrefManager mPrefManager, ServerApiWrapper mApi, DatabaseUtils mDbUtils) {
        super(DataFetcherHousehold.class.getName(), mPrefManager, mApi, mDbUtils);
    }

    @Override
    protected List<HouseholdsReq> getRequirements(Integer agentId) {
        return mApi.getHouseholdRequirements(agentId);
    }

    @Override
    protected List<Households> getRealty(String date) {
        return mApi.getHouseholds(date);
    }

    @Override
    protected Requirement mapRequirement(HouseholdsReq householdsReq) {
        Requirement requirement = new Requirement();
        requirement.id = householdsReq.idRequirements;
        requirement.type = getType();
        requirement.unreadRecommendations =
                mDbUtils.getUnreadRecommendationsCount(requirement.id, requirement.type);
        return requirement;
    }

    @Override
    protected Realty mapRealty(Households households) {
        Realty realty = new Realty();
        realty.id = households.id;
        realty.type = getType();
        return realty;
    }

    @Override
    protected String getType() {
        return RealtyTypes.TYPE_HOUSEHOLD;
    }

    @Override
    protected boolean isMatch(HouseholdsReq householdsReq, Households households) {
        return true;
    }

    @Override
    protected String newLastDate(List<Households> realty) {
        Households lastAdded = Collections.max(realty, new Comparator<Households>() {
            @Override
            public int compare(Households lhs, Households rhs) {
                return lhs.createdAt.compareTo(rhs.createdAt);
            }
        });
        return lastAdded.createdAt;
    }
}

