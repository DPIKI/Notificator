package dpiki.notificator.network;

import java.util.List;

import dpiki.notificator.App;
import dpiki.notificator.data.Realty;
import dpiki.notificator.data.Recommendation;
import dpiki.notificator.data.Requirement;
import dpiki.notificator.network.dataobjects.Rent;
import dpiki.notificator.network.dataobjects.RentReq;

/**
 * Created by Lenovo on 02.08.2016.
 */
public class DataFetcherRent extends DataFetcher<Rent, RentReq> {

    public DataFetcherRent() {
        super(DataFetcherRent.class.getName());
        App.getInstance().inject(this);
    }

    @Override
    protected List<RentReq> getRequirements(Integer agentId) {
        return null;
    }

    @Override
    protected List<Rent> getRealty(String date) {
        return null;
    }

    @Override
    protected Requirement mapRequirement(RentReq rentReq) {
        return null;
    }

    @Override
    protected Realty mapRealty(Rent rent) {
        return null;
    }

    @Override
    protected String getType() {
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
