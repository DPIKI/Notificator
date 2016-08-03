package dpiki.notificator.network;

import java.util.List;

import dpiki.notificator.App;
import dpiki.notificator.data.Realty;
import dpiki.notificator.data.Requirement;
import dpiki.notificator.network.dataobjects.Households;
import dpiki.notificator.network.dataobjects.HouseholdsReq;

/**
 * Created by Lenovo on 02.08.2016.
 */
public class DataFetcherHousehold extends DataFetcher<Households, HouseholdsReq> {

    public DataFetcherHousehold() {
        super(DataFetcherHousehold.class.getName());
        App.getInstance().inject(this);
    }

    @Override
    protected List<HouseholdsReq> getRequirements(Integer agentId) {
        return null;
    }

    @Override
    protected List<Households> getRealty(String date) {
        return null;
    }

    @Override
    protected Requirement mapRequirement(HouseholdsReq householdsReq) {
        return null;
    }

    @Override
    protected Realty mapRealty(Households households) {
        return null;
    }

    @Override
    protected String getType() {
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
