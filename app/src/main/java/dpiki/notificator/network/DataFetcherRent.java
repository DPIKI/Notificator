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
import dpiki.notificator.network.dataobjects.Rent;
import dpiki.notificator.network.dataobjects.RentReq;

/**
 * Created by Lenovo on 02.08.2016.
 */
public class DataFetcherRent extends DataFetcher<Rent, RentReq> {

    public DataFetcherRent(PrefManager mPrefManager, ServerApiWrapper mApi, DatabaseUtils mDbUtils) {
        super(DataFetcherCommercial.class.getName(), mPrefManager, mApi, mDbUtils);
    }

    @Override
    protected List<RentReq> getRequirements(Integer agentId) {
        return mApi.getRentRequirements(agentId);
    }

    @Override
    protected List<Rent> getRealty(String date) {
        return mApi.getRents(date);
    }

    @Override
    protected Requirement mapRequirement(RentReq rentReq) {
        Requirement requirement = new Requirement();
        requirement.id = rentReq.idRequirements;
        requirement.type = getType();
        requirement.unreadRecommendations =
                mDbUtils.getUnreadRecommendationsCount(requirement.id, requirement.type);
        return requirement;
    }

    @Override
    protected Realty mapRealty(Rent rent) {
        Realty realty = new Realty();
        realty.id = rent.id;
        realty.type = getType();
        return realty;
    }

    @Override
    protected String getType() {
        return RealtyTypes.TYPE_RENT;
    }

    @Override
    protected boolean isMatch(RentReq rentReq, Rent rent) {
        return true;
    }

    @Override
    protected String newLastDate(List<Rent> realty) {
        Rent lastAdded = Collections.max(realty, new Comparator<Rent>() {
            @Override
            public int compare(Rent lhs, Rent rhs) {
                return lhs.createdAt.compareTo(rhs.createdAt);
            }
        });
        return lastAdded.createdAt;
    }
}
