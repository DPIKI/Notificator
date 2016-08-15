package dpiki.notificator.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dpiki.notificator.data.RealEstate;
import dpiki.notificator.data.RealtyTypes;
import dpiki.notificator.data.Requisition;

/**
 * Created by Lenovo on 12.08.2016.
 */
public class ServerApiWrapperMock extends ServerApiWrapper {

    @Override
    public List<RealEstate> getRealEstates(String date) throws IOException {
        List<RealEstate> retVal = new ArrayList<>();

        RealEstate r = new RealEstate();
        r.id = 0L;
        r.type = RealtyTypes.TYPE_APARTMENT;
        r.updatedAt = "2016-01-01 12:00:00";
        retVal.add(r);

        r = new RealEstate();
        r.id = 1L;
        r.type = RealtyTypes.TYPE_APARTMENT;
        r.updatedAt = "2016-01-01 12:00:00";
        retVal.add(r);

        r = new RealEstate();
        r.id = 1L;
        r.type = RealtyTypes.TYPE_LAND;
        r.updatedAt = "2016-01-01 12:00:00";
        retVal.add(r);

        return retVal;
    }

    @Override
    public List<Requisition> getRequisitions(Long agentId) throws IOException {
        List<Requisition> retVal = new ArrayList<>();

        Requisition r = new Requisition();
        r.id = 0L;
        r.type = RealtyTypes.TYPE_APARTMENT;
        retVal.add(r);

        r = new Requisition();
        r.id = 1L;
        r.type = RealtyTypes.TYPE_APARTMENT;
        retVal.add(r);

        r = new Requisition();
        r.id = 0L;
        r.type = RealtyTypes.TYPE_LAND;
        retVal.add(r);

        r = new Requisition();
        r.id = 1L;
        r.type = RealtyTypes.TYPE_LAND;
        retVal.add(r);

        r = new Requisition();
        r.id = 2L;
        r.type = RealtyTypes.TYPE_LAND;
        retVal.add(r);

        r = new Requisition();
        r.id = 3L;
        r.type = RealtyTypes.TYPE_LAND;
        retVal.add(r);

        r = new Requisition();
        r.id = 4L;
        r.type = RealtyTypes.TYPE_LAND;
        retVal.add(r);

        r = new Requisition();
        r.id = 5L;
        r.type = RealtyTypes.TYPE_LAND;
        retVal.add(r);

        r = new Requisition();
        r.id = 6L;
        r.type = RealtyTypes.TYPE_LAND;
        retVal.add(r);

        return retVal;
    }
}
