package dpiki.notificator.network;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dpiki.notificator.DatabaseUtils;
import dpiki.notificator.PrefManager;
import dpiki.notificator.data.Realty;
import dpiki.notificator.data.RealtyTypes;
import dpiki.notificator.data.Requirement;
import dpiki.notificator.network.dataobjects.Apartment;
import dpiki.notificator.network.dataobjects.ApartmentReq;

/**
 * Created by Lenovo on 02.08.2016.
 */
public class DataFetcherApartment implements DataFetcherAdapter<Apartment, ApartmentReq> {
    private ServerApiWrapper mWrapper;
    private DatabaseUtils mDbUtils;

    public DataFetcherApartment(ServerApiWrapper wrapper, DatabaseUtils utils) {
        this.mWrapper = wrapper;
        this.mDbUtils = utils;
    }

    @Override
    public List<ApartmentReq> getRequirements(Integer agentId) {
        return mWrapper.getApartmentRequirements(agentId);
    }

    @Override
    public List<Apartment> getRealty(String date) {
        return mWrapper.getApartments(date);
    }

    @Override
    public Requirement mapRequirement(ApartmentReq apartmentReq) {
        Requirement retVal = new Requirement();
        retVal.id = apartmentReq.idRequirements;
        retVal.type = getType();
        retVal.unreadRecommendations =
                mDbUtils.getUnreadRecommendationsCount(retVal.id, retVal.type);
        return retVal;
    }

    @Override
    public Realty mapRealty(Apartment apartment) {
        Realty retVal = new Realty();
        retVal.id = apartment.id;
        retVal.type = getType();
        return retVal;
    }

    @Override
    public String getType() {
        return RealtyTypes.TYPE_APARTMENT;
    }

    @Override
    public boolean isMatch(ApartmentReq apartmentReq, Apartment apartment) {
        return true;
    }

    @Override
    public String newLastDate(List<Apartment> realty) {
        Apartment lastAdded = Collections.max(realty, new Comparator<Apartment>() {
            @Override
            public int compare(Apartment lhs, Apartment rhs) {
                return lhs.createdAt.compareTo(rhs.createdAt);
            }
        });
        return lastAdded.createdAt;
    }
}
