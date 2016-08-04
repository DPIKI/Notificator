package dpiki.notificator.network;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dpiki.notificator.App;
import dpiki.notificator.data.Realty;
import dpiki.notificator.data.RealtyTypes;
import dpiki.notificator.data.Requirement;
import dpiki.notificator.network.dataobjects.Apartment;
import dpiki.notificator.network.dataobjects.ApartmentReq;

/**
 * Created by Lenovo on 02.08.2016.
 */
public class DataFetcherApartment extends DataFetcher<Apartment, ApartmentReq> {

    public DataFetcherApartment() {
        super(DataFetcherApartment.class.getName());
        App.getInstance().inject(this);
    }

    @Override
    protected List<ApartmentReq> getRequirements(Integer agentId) {
        return mApi.getApartmentRequirements(agentId);
    }

    @Override
    protected List<Apartment> getRealty(String date) {
        return mApi.getApartments(date);
    }

    @Override
    protected Requirement mapRequirement(ApartmentReq apartmentReq) {
        Requirement retVal = new Requirement();
        retVal.id = apartmentReq.idRequirements;
        retVal.type = getType();
        retVal.unreadRecommendations =
                mDbUtils.getUnreadRecommendationsCount(retVal.id, retVal.type);
        return retVal;
    }

    @Override
    protected Realty mapRealty(Apartment apartment) {
        Realty retVal = new Realty();
        retVal.id = apartment.id;
        retVal.type = getType();
        return retVal;
    }

    @Override
    protected String getType() {
        return RealtyTypes.TYPE_APARTMENT;
    }

    @Override
    protected boolean isMatch(ApartmentReq apartmentReq, Apartment apartment) {
        return true;
    }

    @Override
    protected String newLastDate(List<Apartment> realty) {
        Apartment lastAdded = Collections.max(realty, new Comparator<Apartment>() {
            @Override
            public int compare(Apartment lhs, Apartment rhs) {
                return lhs.createdAt.compareTo(rhs.createdAt);
            }
        });
        return lastAdded.createdAt;
    }
}
