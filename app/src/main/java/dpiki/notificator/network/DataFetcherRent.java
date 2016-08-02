package dpiki.notificator.network;

import java.util.List;

import dpiki.notificator.data.Recommendation;
import dpiki.notificator.network.dataobjects.Rent;
import dpiki.notificator.network.dataobjects.RentReq;

/**
 * Created by Lenovo on 02.08.2016.
 */
public class DataFetcherRent extends DataFetcher<Rent, RentReq> {

    public DataFetcherRent() {
        super(DataFetcherRent.class.getName());
    }

    @Override
    protected List<RentReq> getRequirements() {
        return null;
    }

    @Override
    protected List<Rent> getRealty(String date) {
        return null;
    }

    @Override
    protected Recommendation makeRecommendation(RentReq rentReq, Rent rent) {
        return null;
    }

    @Override
    protected boolean isMatch(RentReq rentReq, Rent rent) {
        return false;
    }

    @Override
    protected String newLastDate(List<Rent> realty) {
        return null;
    }
}
