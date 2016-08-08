package dpiki.notificator.network;

import java.util.ArrayList;
import java.util.List;

import dpiki.notificator.data.RealtyTypes;
import dpiki.notificator.network.dataobjects.Apartment;
import dpiki.notificator.network.dataobjects.ApartmentReq;
import dpiki.notificator.network.dataobjects.RealtyBase;
import dpiki.notificator.network.dataobjects.RequirementBase;

/**
 * Created by Lenovo on 02.08.2016.
 */
public class DataFetcherApartmentAdapter implements DataFetcherAdapter {
    private ServerApiWrapper mWrapper;

    public DataFetcherApartmentAdapter(ServerApiWrapper wrapper) {
        this.mWrapper = wrapper;
    }

    @Override
    public List<RequirementBase> getRequirements(Integer agentId) {
        List<RequirementBase> retVal = new ArrayList<>();
        List<ApartmentReq> r = mWrapper.getApartmentRequirements(agentId);
        if (r != null) {
            retVal.addAll(r);
        }
        return retVal;
    }

    @Override
    public List<RealtyBase> getRealty(String date) {
        List<RealtyBase> retVal = new ArrayList<>();
        List<Apartment> r = mWrapper.getApartments(date);
        if (r != null) {
            retVal.addAll(r);
        }
        return retVal;
    }

    @Override
    public String getType() {
        return RealtyTypes.TYPE_APARTMENT;
    }
}
