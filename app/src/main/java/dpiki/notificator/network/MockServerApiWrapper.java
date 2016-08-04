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
    public List<Apartment> getApartments(String date) {
        List<Apartment> list = new ArrayList<>();
        Apartment apartment = new Apartment();
        apartment.id = 900;
        apartment.createdAt = "2016-09-08 20:20:20";
        list.add(apartment);
        return list;
    }

    @Override
    public List<ApartmentReq> getApartmentRequirements(Integer agentId) {
        List<ApartmentReq> reqList = new ArrayList<>();

        ApartmentReq apartmentReq = new ApartmentReq();
        apartmentReq.idRequirements = 8;
        reqList.add(apartmentReq);

        apartmentReq = new ApartmentReq();
        apartmentReq.idRequirements = 3;
        reqList.add(apartmentReq);

        apartmentReq = new ApartmentReq();
        apartmentReq.idRequirements = 5;
        reqList.add(apartmentReq);

        apartmentReq = new ApartmentReq();
        apartmentReq.idRequirements = 1;
        reqList.add(apartmentReq);

        apartmentReq = new ApartmentReq();
        apartmentReq.idRequirements = 888;
        reqList.add(apartmentReq);

        apartmentReq = new ApartmentReq();
        apartmentReq.idRequirements = 7373;
        reqList.add(apartmentReq);

        return reqList;
    }

    @Override
    public List<Land> getLands(String date) {
        return new ArrayList<>();
    }

    @Override
    public List<LandReq> getLandRequirements(Integer agentId) {
        List<LandReq> reqList = new ArrayList<>();

        LandReq landReq = new LandReq();
        landReq.idRequirements = 18;
        reqList.add(landReq);

        landReq = new LandReq();
        landReq.idRequirements = 13;
        reqList.add(landReq);

        landReq = new LandReq();
        landReq.idRequirements = 15;
        reqList.add(landReq);

        landReq = new LandReq();
        landReq.idRequirements = 11;
        reqList.add(landReq);

        landReq = new LandReq();
        landReq.idRequirements = 1888;
        reqList.add(landReq);

        landReq = new LandReq();
        landReq.idRequirements = 17373;
        reqList.add(landReq);

        return reqList;
    }

    @Override
    public List<Rent> getRents(String date) {
        return new ArrayList<>();
    }

    @Override
    public List<RentReq> getRentRequirements(Integer agentId) {
        return new ArrayList<>();
    }

    @Override
    public List<Households> getHouseholds(String date) {
        return new ArrayList<>();
    }

    @Override
    public List<HouseholdsReq> getHouseholdRequirements(Integer agentId) {
        return new ArrayList<>();
    }

    @Override
    public List<Commercial> getCommercials(String date) {
        return new ArrayList<>();
    }

    @Override
    public List<CommercialReq> getCommercialRequirements(Integer agentId) {
        return new ArrayList<>();
    }
}
