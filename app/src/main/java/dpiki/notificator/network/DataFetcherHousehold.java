package dpiki.notificator.network;

import java.util.List;

import dpiki.notificator.data.Recommendation;
import dpiki.notificator.network.dataobjects.Households;
import dpiki.notificator.network.dataobjects.HouseholdsReq;

/**
 * Created by Lenovo on 02.08.2016.
 */
public class DataFetcherHousehold extends DataFetcher<Households, HouseholdsReq> {

    public DataFetcherHousehold() {
        super(DataFetcherHousehold.class.getName());
    }

    @Override
    protected List<HouseholdsReq> getRequirements() {
        return null;
    }

    @Override
    protected List<Households> getRealty(String date) {
        return null;
    }

    @Override
    protected Recommendation makeRecommendation(HouseholdsReq householdsReq, Households households) {
        return null;
    }

    @Override
    protected boolean isMatch(HouseholdsReq householdsReq, Households households) {
        return false;
    }

    @Override
    protected String newLastDate(List<Households> realty) {
        return null;
    }
}
