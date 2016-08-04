package dpiki.notificator.network;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dpiki.notificator.App;
import dpiki.notificator.data.Realty;
import dpiki.notificator.data.RealtyTypes;
import dpiki.notificator.data.Requirement;
import dpiki.notificator.network.dataobjects.Commercial;
import dpiki.notificator.network.dataobjects.CommercialReq;

/**
 * Created by Lenovo on 02.08.2016.
 */
public class DataFetcherCommercial extends DataFetcher<Commercial, CommercialReq> {

    public DataFetcherCommercial() {
        super(DataFetcherCommercial.class.getName());
        App.getInstance().inject(this);
    }

    @Override
    protected List<CommercialReq> getRequirements(Integer agentId) {
        return mApi.getCommercialRequirements(agentId);
    }

    @Override
    protected List<Commercial> getRealty(String date) {
        return mApi.getCommercials(date);
    }

    @Override
    protected Requirement mapRequirement(CommercialReq commercialReq) {
        Requirement requirement = new Requirement();
        requirement.id = commercialReq.idRequirements;
        requirement.type = getType();
        requirement.unreadRecommendations =
                mDbUtils.getUnreadRecommendationsCount(requirement.id, requirement.type);
        return requirement;
    }

    @Override
    protected Realty mapRealty(Commercial commercial) {
        Realty realty = new Realty();
        realty.id = commercial.id;
        realty.type = getType();
        return realty;
    }

    @Override
    protected String getType() {
        return RealtyTypes.TYPE_COMMERCIAL;
    }

    @Override
    protected boolean isMatch(CommercialReq commercialReq, Commercial commercial) {
        return true;
    }

    @Override
    protected String newLastDate(List<Commercial> realty) {
        Commercial lastAdded = Collections.max(realty, new Comparator<Commercial>() {
            @Override
            public int compare(Commercial lhs, Commercial rhs) {
                return lhs.createdAt.compareTo(rhs.createdAt);
            }
        });
        return lastAdded.createdAt;
    }
}
