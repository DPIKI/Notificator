package dpiki.notificator.network;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dpiki.notificator.DatabaseUtils;
import dpiki.notificator.PrefManager;
import dpiki.notificator.data.RealtyTypes;
import dpiki.notificator.data.Recommendation;
import dpiki.notificator.data.Requirement;
import dpiki.notificator.network.dataobjects.Apartment;
import dpiki.notificator.network.dataobjects.ApartmentReq;
import dpiki.notificator.network.dataobjects.Commercial;
import dpiki.notificator.network.dataobjects.CommercialReq;
import dpiki.notificator.network.dataobjects.Household;
import dpiki.notificator.network.dataobjects.HouseholdReq;
import dpiki.notificator.network.dataobjects.Land;
import dpiki.notificator.network.dataobjects.LandReq;
import dpiki.notificator.network.dataobjects.RealtyBase;
import dpiki.notificator.network.dataobjects.Rent;
import dpiki.notificator.network.dataobjects.RentReq;
import dpiki.notificator.network.dataobjects.RequirementBase;
import dpiki.notificator.network.dataobjects.RequirementContainer;
import dpiki.notificator.network.gson.Communication;
import dpiki.notificator.network.gson.LiftingEquipment;
import dpiki.notificator.network.gson.Realestate;
import dpiki.notificator.network.gson.RealestateInfo;
import dpiki.notificator.network.gson.SearchNearContainer;
import dpiki.notificator.network.gson.TypeRent;

/**
 * Created by Lenovo on 03.08.2016.
 */
public class DataFetcher {
    private final List<RealtyBase> apartments = new ArrayList<>();
    private final List<RequirementBase> apartmentReqs = new ArrayList<>();
    private final List<RealtyBase> rents = new ArrayList<>();
    private final List<RequirementBase> rentReqs = new ArrayList<>();
    private final List<RealtyBase> lands = new ArrayList<>();
    private final List<RequirementBase> landReqs = new ArrayList<>();
    private final List<RealtyBase> households = new ArrayList<>();
    private final List<RequirementBase> householdReqs = new ArrayList<>();
    private final List<RealtyBase> commercials = new ArrayList<>();
    private final List<RequirementBase> commercialReqs = new ArrayList<>();
    private PrefManager mPrefManager;
    private DatabaseUtils mDbUtils;
    private ServerApiWrapper mApi;

    public DataFetcher(PrefManager mPrefManager, DatabaseUtils mDbUtils, ServerApiWrapper mApi) {
        this.mPrefManager = mPrefManager;
        this.mDbUtils = mDbUtils;
        this.mApi = mApi;
    }

    public List<Recommendation> fetch() {
        List<Recommendation> retVal = new ArrayList<>();

        try {
            downloadRequirements();
            downloadRealty();
            retVal.addAll(handleData(apartmentReqs, apartments, RealtyTypes.TYPE_APARTMENT));
            retVal.addAll(handleData(rentReqs, rents, RealtyTypes.TYPE_RENT));
            retVal.addAll(handleData(landReqs, lands, RealtyTypes.TYPE_LAND));
            retVal.addAll(handleData(householdReqs, households, RealtyTypes.TYPE_HOUSEHOLD));
            retVal.addAll(handleData(commercialReqs, commercials, RealtyTypes.TYPE_COMMERCIAL));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return retVal;
    }

    private void downloadRealty() throws IOException {
        apartments.clear();
        rents.clear();
        lands.clear();
        households.clear();
        commercials.clear();

        String strLastDate = mPrefManager.getLastFetchDate();
        mPrefManager.putLastFetchDate(strLastDate);

        List<SearchNearContainer> realty = mApi.getRealty(strLastDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (SearchNearContainer i : realty) {
            if (i.getRealestate() == null) { // TODO : спросить
                throw new IOException("Invalid response: realEstate == null");
            }

            if (i.getRealestate().getRealestateInstanceType().contains("Apartment")) {
                apartments.add(makeApartment(i));
            } else if (i.getRealestate().getRealestateInstanceType().contains("Rent")) {
                rents.add(makeRent(i));
            } else if (i.getRealestate().getRealestateInstanceType().contains("Land")) {
                lands.add(makeLand(i));
            } else if (i.getRealestate().getRealestateInstanceType().contains("Household")) {
                households.add(makeHousehold(i));
            } else if (i.getRealestate().getRealestateInstanceType().contains("Commercial")) {
                commercials.add(makeCommercial(i));
            }

            if (i.getRealestate().getUpdatedAt() == null) {
                throw new IOException("Invalid response: updateAt == null");
            }

            String strUpdatedDate = sdf.format(i.getRealestate().getUpdatedAt());
            if (strUpdatedDate.compareTo(strLastDate) > 0)
                strLastDate = strUpdatedDate;
        }

        mPrefManager.putLastFetchDate(strLastDate);
    }

    private void downloadRequirements() throws IOException {
        apartmentReqs.clear();
        landReqs.clear();
        rentReqs.clear();
        householdReqs.clear();
        commercialReqs.clear();

        List<RequirementContainer> response = mApi.getRequirements(0L); // TODO: real agent id
        List<Requirement> requirements = new ArrayList<>();

        for (RequirementContainer i : response) {
            Requirement r = new Requirement();
            if (i.getRequirementInstanceType().contains(RealtyTypes.TYPE_APARTMENT)) {
                apartmentReqs.add(makeApartmentReqs(i));
                r.type = RealtyTypes.TYPE_APARTMENT;
            } else if (i.getRequirementInstanceType().contains(RealtyTypes.TYPE_RENT)) {
                rentReqs.add(makeRentReqs(i));
                r.type = RealtyTypes.TYPE_RENT;
            } else if (i.getRequirementInstanceType().contains(RealtyTypes.TYPE_LAND)) {
                landReqs.add(makeLandReqs(i));
                r.type = RealtyTypes.TYPE_LAND;
            } else if (i.getRequirementInstanceType().contains(RealtyTypes.TYPE_HOUSEHOLD)) {
                householdReqs.add(makeHouseholdReqs(i));
                r.type = RealtyTypes.TYPE_HOUSEHOLD;
            } else if (i.getRequirementInstanceType().contains(RealtyTypes.TYPE_COMMERCIAL)) {
                commercialReqs.add(makeCommercialReqs(i));
                r.type = RealtyTypes.TYPE_COMMERCIAL;
            }
            r.id = i.getId();
            r.unreadRecommendations = mDbUtils.getUnreadRecommendationsCount(r.id, r.type);
            requirements.add(r);
        }

        mDbUtils.updateRequirements(requirements);
    }

    private CommercialReq makeCommercialReqs(RequirementContainer i) {
        CommercialReq req = new CommercialReq();
        req.idRequirements = i.getId();
        // TODO : implement
        return req;
    }

    private HouseholdReq makeHouseholdReqs(RequirementContainer i) {
        HouseholdReq req = new HouseholdReq();
        req.idRequirements = i.getId();
        // TODO : implement
        return req;
    }

    private LandReq makeLandReqs(RequirementContainer i) {
        LandReq req = new LandReq();
        req.idRequirements = i.getId();
        // TODO : implement
        return req;
    }

    private RentReq makeRentReqs(RequirementContainer i) {
        RentReq req = new RentReq();
        req.idRequirements = i.getId();
        // TODO : implement
        return req;
    }

    private ApartmentReq makeApartmentReqs(RequirementContainer i) {
        ApartmentReq req = new ApartmentReq();
        req.idRequirements = i.getId();
        // TODO : implement
        return req;
    }

    private Apartment makeApartment(SearchNearContainer container) {
        Apartment retVal = new Apartment();
        Realestate realEstate = container.getRealestate();
        RealestateInfo realEstateInfo = null;
        try {
            realEstateInfo = container.getRealestateType();
        } catch (NullPointerException e) {
        }

        retVal.id = realEstate != null ? realEstate.getId() : null;
        retVal.idAddress = realEstate != null ? realEstate.getIdAddress() : null;
        retVal.firm = realEstate != null ? realEstate.getIsFirmContactPerson() : null;
        retVal.cost = realEstateInfo != null ? realEstateInfo.getCost() : null;
        retVal.floor = realEstateInfo != null ? realEstateInfo.getFloor() : null;
        retVal.floorAll = realEstateInfo != null ? realEstateInfo.getFloorAll() : null;
        retVal.idFund = realEstateInfo != null ? realEstateInfo.getIdFund() : null;
        retVal.idState = realEstateInfo != null ? realEstateInfo.getIdState() : null;
        retVal.idTypeApartment = realEstateInfo != null ? realEstateInfo.getIdTypeApartment() : null;
        retVal.idWallMaterial = realEstateInfo != null ? realEstateInfo.getIdWallMaterial() : null;
        retVal.kitchenArea = realEstateInfo != null ? realEstateInfo.getKitchenArea() : null;
        retVal.livingArea = realEstateInfo != null ? realEstateInfo.getLivingArea() : null;
        retVal.totalArea = realEstateInfo != null ? realEstateInfo.getTotalArea() : null;

        return retVal;
    }

    private Land makeLand(SearchNearContainer container) {
        Land retVal = new Land();
        Realestate realEstate = container.getRealestate();
        RealestateInfo realEstateInfo = null;
        try {
            realEstateInfo = container.getRealestateType();
        } catch (NullPointerException e) {
        }

        retVal.id = realEstate != null ? realEstate.getId() : null;
        retVal.idAddress = realEstate != null ? realEstate.getIdAddress() : null;
        retVal.firm = realEstate != null ? realEstate.getIsFirmContactPerson() : null;
        retVal.cost = realEstateInfo != null ? realEstateInfo.getCost() : null;
        retVal.idEntry = realEstateInfo != null ? realEstateInfo.getIdEntry() : null;
        retVal.idFurniture = realEstateInfo != null ? realEstateInfo.getIdFurniture() : null;
        retVal.idState = realEstateInfo != null ? realEstateInfo.getIdState() : null;
        retVal.idWallMaterial = realEstateInfo != null ? realEstateInfo.getIdWallMaterial() : null;
        retVal.kitchenArea = realEstateInfo != null ? realEstateInfo.getKitchenArea() : null;
        retVal.livingArea = realEstateInfo != null ? realEstateInfo.getLivingArea() : null;
        retVal.totalArea = realEstateInfo != null ? realEstateInfo.getTotalArea() : null;
        retVal.stead = realEstateInfo != null ? realEstateInfo.getStead() : null;

        return retVal;
    }

    private Rent makeRent(SearchNearContainer container) {
        Rent retVal = new Rent();
        Realestate realEstate = container.getRealestate();
        RealestateInfo realEstateInfo = null;
        try {
            realEstateInfo = container.getRealestateType();
        } catch (NullPointerException e) {
        }

        retVal.id = realEstate != null ? realEstate.getId() : null;
        retVal.firm = realEstate != null ? realEstate.getIsFirmContactPerson() : null;
        retVal.idAddress = realEstate != null ? realEstate.getIdAddress() : null;
        retVal.cost = realEstateInfo != null ? realEstateInfo.getCost() : null;
        retVal.floor = realEstateInfo != null ? realEstateInfo.getFloor() : null;
        retVal.floorAll = realEstateInfo != null ? realEstateInfo.getFloorAll() : null;
        retVal.hasPhone = realEstateInfo != null ? realEstateInfo.getHasPhone() : null;
        retVal.idComfort = realEstateInfo != null ? realEstateInfo.getIdComfort() : null;
        retVal.idFurniture = realEstateInfo != null ? realEstateInfo.getIdFurniture() : null;
        retVal.idState = realEstateInfo != null ? realEstateInfo.getIdState() : null;
        retVal.idYard = realEstateInfo != null ? realEstateInfo.getIdYard() : null;
        retVal.roomCount = realEstateInfo != null ? realEstateInfo.getRoomCount() : null;
        retVal.prepayment = realEstateInfo != null ? realEstateInfo.getPrepayment() : null;
        retVal.idEntry = realEstateInfo != null ? realEstateInfo.getIdEntry() : null;
        retVal.idTypeApartment = realEstateInfo != null ? realEstateInfo.getIdTypeApartment() : null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        retVal.dateFreed = realEstateInfo != null ? sdf.format(realEstateInfo.getDatePuttingHouse()) : null;

        TypeRent[] foo = realEstateInfo != null ? realEstateInfo.getTypeRent() : null;
        if (foo != null) {
            retVal.idRent = new Long[foo.length];
            for (int i = 0; i < foo.length; i++) {
                retVal.idRent[i] = foo[i].getId();
            }
        }

        return retVal;
    }

    private Commercial makeCommercial(SearchNearContainer container) {
        Commercial retVal = new Commercial();
        Realestate realEstate = container.getRealestate();
        RealestateInfo realEstateInfo = null;
        try {
            realEstateInfo = container.getRealestateType();
        } catch (NullPointerException e) {
        }

        retVal.id = realEstate != null ? realEstate.getId() : null;
        retVal.idAddress = realEstate != null ? realEstate.getIdAddress() : null;
        retVal.firm = realEstate != null ? realEstate.getIsFirmContactPerson() : null;
        retVal.hallArea = realEstateInfo != null ? realEstateInfo.getHallArea() : null;
        retVal.landArea = realEstateInfo != null ? realEstateInfo.getLandArea() : null;
        retVal.totalArea = realEstateInfo != null ? realEstateInfo.getTotalArea() : null;
        retVal.rentArea = realEstateInfo != null ? realEstateInfo.getRentArea() : null;
        retVal.sellPrice = realEstateInfo != null ? realEstateInfo.getSellPrice() : null;
        retVal.sellPriceSquareMeter = realEstateInfo != null ? realEstateInfo.getSellPriceSquareMeter() : null;
        retVal.rentPrice = realEstateInfo != null ? realEstateInfo.getRentPrice() : null;
        retVal.rentPriceSquareMeter = realEstateInfo != null ? realEstateInfo.getRentPriceSquareMeter() : null;

        LiftingEquipment[] foo = realEstateInfo != null ? realEstateInfo.getLiftingEquipment() : null;
        if (foo != null) {
            retVal.idLiftingEquipments = new Long[foo.length];
            for (int i = 0; i < foo.length; i++) {
                retVal.idLiftingEquipments[i] = foo[i].getId();
            }
        }

        Communication[] bar = realEstateInfo != null ? realEstateInfo.getCommunication() : null;
        if (bar != null) {
            retVal.idCommunications = new Long[bar.length];
            for (int i = 0; i < bar.length; i++) {
                retVal.idCommunications[i] = bar[i].getId();
            }
        }

        return retVal;
    }

    private Household makeHousehold(SearchNearContainer container) {
        Household retVal = new Household();
        Realestate realEstate = container.getRealestate();
        RealestateInfo realEstateInfo = null;
        try {
            realEstateInfo = container.getRealestateType();
        } catch (NullPointerException e) {
        }

        retVal.id = realEstate != null ? realEstate.getId() : null;
        retVal.idAddress = realEstate != null ? realEstate.getIdAddress() : null;
        retVal.firm = realEstate != null ? realEstate.getIsFirmContactPerson() : null;
        retVal.cost =  realEstateInfo != null ? realEstateInfo.getCost() : null;
        retVal.idEntry = realEstateInfo != null ? realEstateInfo.getIdEntry() : null;
        retVal.idFurniture = realEstateInfo != null ? realEstateInfo.getIdFurniture() : null;
        retVal.idState = realEstateInfo != null ? realEstateInfo.getIdState() : null;
        retVal.idWallMaterial = realEstateInfo != null ? realEstateInfo.getIdWallMaterial() : null;
        retVal.kitchenArea = realEstateInfo != null ? realEstateInfo.getKitchenArea() : null;
        retVal.livingArea = realEstateInfo != null ? realEstateInfo.getLivingArea() : null;
        retVal.totalArea = realEstateInfo != null ? realEstateInfo.getTotalArea() : null;
        retVal.stead = realEstateInfo != null ? realEstateInfo.getStead() : null;

        return retVal;
    }

    private List<Recommendation> handleData(List<RequirementBase> requirements,
                                            List<RealtyBase> realty, String type) {
        List<Recommendation> retVal = new ArrayList<>();

        if (requirements.isEmpty())
            return retVal;

        if (realty.isEmpty())
            return retVal;

        for (RequirementBase i : requirements) {
            for (RealtyBase j : realty) {
                if (j.isMatch(i)) {
                    retVal.add(new Recommendation(i.idRequirements, j.id, type));
                }
            }
        }

        return retVal;
    }

}
