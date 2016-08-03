package dpiki.notificator.network;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dpiki.notificator.App;
import dpiki.notificator.DatabaseHelper;
import dpiki.notificator.DatabaseUtils;
import dpiki.notificator.data.Realty;
import dpiki.notificator.data.RealtyTypes;
import dpiki.notificator.data.Requirement;
import dpiki.notificator.network.dataobjects.Land;
import dpiki.notificator.network.dataobjects.LandReq;

/**
 * Created by Lenovo on 02.08.2016.
 */
public class DataFetcherLand extends DataFetcher<Land, LandReq> {

    public DataFetcherLand() {
        super(DataFetcherLand.class.getName());
        App.getInstance().inject(this);
    }

    @Override
    protected List<LandReq> getRequirements(Integer agentId) {
        return mApi.getLandRequirements(agentId);
    }

    @Override
    protected List<Land> getRealty(String date) {
        return mApi.getLands(date);
    }

    @Override
    protected Requirement mapRequirement(LandReq landReq) {
        Requirement requirement = new Requirement();
        requirement.id = landReq.id;
        requirement.type = getType();
        requirement.unreadRecommendations =
                mDbUtils.getUnreadRecommendationsCount(requirement.id, requirement.type);
        return requirement;
    }

    @Override
    protected Realty mapRealty(Land land) {
        Realty realty = new Realty();
        realty.id = land.id;
        realty.type = getType();
        return realty;
    }

    @Override
    protected String getType() {
        return RealtyTypes.TYPE_LAND;
    }

    @Override
    protected boolean isMatch(LandReq landReq, Land land) {
        return true;
    }

    @Override
    protected String newLastDate(List<Land> realty) {
        Land lastAdded = Collections.max(realty, new Comparator<Land>() {
            @Override
            public int compare(Land lhs, Land rhs) {
                return lhs.createdAt.compareTo(rhs.createdAt);
            }
        });
        return lastAdded.createdAt;
    }

}
