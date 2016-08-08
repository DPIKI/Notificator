package dpiki.notificator.network;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dpiki.notificator.App;
import dpiki.notificator.DatabaseUtils;
import dpiki.notificator.PrefManager;
import dpiki.notificator.data.Realty;
import dpiki.notificator.data.RealtyTypes;
import dpiki.notificator.data.Requirement;
import dpiki.notificator.network.dataobjects.Households;
import dpiki.notificator.network.dataobjects.HouseholdsReq;
import dpiki.notificator.network.dataobjects.RealtyBase;
import dpiki.notificator.network.dataobjects.RequirementBase;

/**
 * Created by Lenovo on 02.08.2016.
 */
public class DataFetcherHouseholdAdapter implements DataFetcherAdapter {
    private ServerApiWrapper mWrapper;

    public DataFetcherHouseholdAdapter(ServerApiWrapper mWrapper) {
        this.mWrapper = mWrapper;
    }

    @Override
    public List<RequirementBase> getRequirements(Integer agentId) {
        List<RequirementBase> retVal = new ArrayList<>();
        List<HouseholdsReq> r = mWrapper.getHouseholdRequirements(agentId);
        if (r != null) {
            retVal.addAll(r);
        }
        return retVal;
    }

    @Override
    public List<RealtyBase> getRealty(String date) {
        List<RealtyBase> retVal = new ArrayList<>();
        List<Households> r = mWrapper.getHouseholds(date);
        if (r != null) {
            retVal.addAll(r);
        }
        return retVal;
    }

    @Override
    public String getType() {
        return RealtyTypes.TYPE_HOUSEHOLD;
    }
}

