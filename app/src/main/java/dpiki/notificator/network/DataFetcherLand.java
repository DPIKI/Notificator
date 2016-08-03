package dpiki.notificator.network;

import java.util.List;

import dpiki.notificator.data.Realty;
import dpiki.notificator.data.Recommendation;
import dpiki.notificator.data.Requirement;
import dpiki.notificator.network.dataobjects.Land;
import dpiki.notificator.network.dataobjects.LandReq;

/**
 * Created by Lenovo on 02.08.2016.
 */
public class DataFetcherLand extends DataFetcher<Land, LandReq> {

    public DataFetcherLand() {
        super(DataFetcherLand.class.getName());
    }

    @Override
    protected List<LandReq> getRequirements(Integer agentId) {
        return null;
    }

    @Override
    protected List<Land> getRealty(String date) {
        return null;
    }

    @Override
    protected Requirement mapRequirement(LandReq landReq) {
        return null;
    }

    @Override
    protected Realty mapRealty(Land land) {
        return null;
    }

    @Override
    protected String getType() {
        return null;
    }

    @Override
    protected boolean isMatch(LandReq landReq, Land land) {
        return false;
    }

    @Override
    protected String newLastDate(List<Land> realty) {
        return null;
    }
}
