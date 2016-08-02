package dpiki.notificator.network;

import java.util.List;

import dpiki.notificator.data.Recommendation;
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
    protected List<LandReq> getRequirements() {
        return null;
    }

    @Override
    protected List<Land> getRealty(String date) {
        return null;
    }

    @Override
    protected Recommendation makeRecommendation(LandReq landReq, Land land) {
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
