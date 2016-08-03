package dpiki.notificator.network;

import java.util.List;

import dpiki.notificator.data.Realty;
import dpiki.notificator.data.Requirement;
import dpiki.notificator.network.dataobjects.Commercial;
import dpiki.notificator.network.dataobjects.CommercialReq;

/**
 * Created by Lenovo on 02.08.2016.
 */
public class DataFetcherCommercial extends DataFetcher<Commercial, CommercialReq> {

    public DataFetcherCommercial() {
        super(DataFetcherCommercial.class.getName());
    }

    @Override
    protected List<CommercialReq> getRequirements(Integer agentId) {
        return null;
    }

    @Override
    protected List<Commercial> getRealty(String date) {
        return null;
    }

    @Override
    protected Requirement mapRequirement(CommercialReq commercialReq) {
        return null;
    }

    @Override
    protected Realty mapRealty(Commercial commercial) {
        return null;
    }

    @Override
    protected String getType() {
        return null;
    }

    @Override
    protected boolean isMatch(CommercialReq commercialReq, Commercial commercial) {
        return false;
    }

    @Override
    protected String newLastDate(List<Commercial> realty) {
        return null;
    }
}
