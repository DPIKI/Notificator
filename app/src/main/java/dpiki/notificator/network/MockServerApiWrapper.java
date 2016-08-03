package dpiki.notificator.network;

import java.util.ArrayList;
import java.util.List;

import dpiki.notificator.network.dataobjects.Apartment;
import dpiki.notificator.network.dataobjects.ApartmentReq;
import dpiki.notificator.network.dataobjects.Commercial;
import dpiki.notificator.network.dataobjects.CommercialReq;
import dpiki.notificator.network.dataobjects.Households;
import dpiki.notificator.network.dataobjects.HouseholdsReq;
import dpiki.notificator.network.dataobjects.Land;
import dpiki.notificator.network.dataobjects.LandReq;
import dpiki.notificator.network.dataobjects.Rent;
import dpiki.notificator.network.dataobjects.RentReq;

/**
 * Created by prog1 on 03.08.2016.
 */
public class MockServerApiWrapper extends ServerApiWrapper{

    public MockServerApiWrapper() {

    }

    @Override
    List<Apartment> getApartments(String date) {
        return new ArrayList<>();
    }

    @Override
    List<ApartmentReq> getApartmentRequirements(Integer agentId) {
        List<ApartmentReq> reqList = new ArrayList<>();
        ApartmentReq apartmentReq = new ApartmentReq();
        apartmentReq.id = 8;
        reqList.add(apartmentReq);
        return reqList;
    }

    @Override
    List<Land> getLands(String date) {
        return new ArrayList<>();
    }

    @Override
    List<LandReq> getLandRequirements(Integer agentId) {
        return new ArrayList<>();
    }

    @Override
    List<Rent> getRents(String date) {
        return new ArrayList<>();
    }

    @Override
    List<RentReq> getRentRequirements(Integer agentId) {
        return new ArrayList<>();
    }

    @Override
    List<Households> getHouseholds(String date) {
        return new ArrayList<>();
    }

    @Override
    List<HouseholdsReq> getHouseholdRequirements(Integer agentId) {
        return new ArrayList<>();
    }

    @Override
    List<Commercial> getCommercials(String date) {
        return new ArrayList<>();
    }

    @Override
    List<CommercialReq> getCommercialRequirements(Integer agentId) {
        return new ArrayList<>();
    }
}
