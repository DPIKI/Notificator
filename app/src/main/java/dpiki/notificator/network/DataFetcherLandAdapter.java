package dpiki.notificator.network;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dpiki.notificator.App;
import dpiki.notificator.DatabaseHelper;
import dpiki.notificator.DatabaseUtils;
import dpiki.notificator.PrefManager;
import dpiki.notificator.data.Realty;
import dpiki.notificator.data.RealtyTypes;
import dpiki.notificator.data.Requirement;
import dpiki.notificator.network.dataobjects.Land;
import dpiki.notificator.network.dataobjects.LandReq;
import dpiki.notificator.network.dataobjects.RealtyBase;
import dpiki.notificator.network.dataobjects.RequirementBase;

/**
 * Created by Lenovo on 02.08.2016.
 */
public class DataFetcherLandAdapter implements DataFetcherAdapter {
    private ServerApiWrapper mWrapper;

    public DataFetcherLandAdapter(ServerApiWrapper mWrapper) {
        this.mWrapper = mWrapper;
    }

    @Override
    public List<RequirementBase> getRequirements(Integer agentId) {
        List<RequirementBase> retVal = new ArrayList<>();
        retVal.addAll(mWrapper.getLandRequirements(agentId));
        return retVal;
    }

    @Override
    public List<RealtyBase> getRealty(String date) {
        List<RealtyBase> retVal = new ArrayList<>();
        retVal.addAll(mWrapper.getLands(date));
        return retVal;
    }

    @Override
    public String getType() {
        return RealtyTypes.TYPE_LAND;
    }
}
