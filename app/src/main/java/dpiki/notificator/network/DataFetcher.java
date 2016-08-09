package dpiki.notificator.network;

import com.google.gson.GsonBuilder;

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
import dpiki.notificator.network.dataobjects.Commercial;
import dpiki.notificator.network.dataobjects.Households;
import dpiki.notificator.network.dataobjects.Land;
import dpiki.notificator.network.dataobjects.RealtyBase;
import dpiki.notificator.network.dataobjects.Rent;
import dpiki.notificator.network.dataobjects.RequirementBase;
import dpiki.notificator.network.gson.Communication;
import dpiki.notificator.network.gson.LiftingEquipment;
import dpiki.notificator.network.gson.SearchNearContainer;
import dpiki.notificator.network.gson.TypeRent;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        downloadRequirements();
        downloadRealty();

        retVal.addAll(handleDataByType(RealtyTypes.TYPE_APARTMENT));
        retVal.addAll(handleDataByType(RealtyTypes.TYPE_RENT));
        retVal.addAll(handleDataByType(RealtyTypes.TYPE_LAND));
        retVal.addAll(handleDataByType(RealtyTypes.TYPE_COMMERCIAL));
        retVal.addAll(handleDataByType(RealtyTypes.TYPE_HOUSEHOLD));

        return retVal;
    }

    private void downloadRealty() {
        apartments.clear();
        rents.clear();
        lands.clear();
        households.clear();
        commercials.clear();

        String strLastDate = mPrefManager.getLastFetchDate();
        mPrefManager.putLastFetchDate(strLastDate);

        try {
            List<SearchNearContainer> realty = mApi.getRealty(strLastDate);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            for (SearchNearContainer i : realty) {
                if (i.getRealestate().getRealestateInstanceType().contains("Apartment")) {
                    apartments.add(makeApartment(i));
                } else if (i.getRealestate().getRealestateInstanceType().contains("Rent")) {
                    rents.add(makeRent(i));
                } else if (i.getRealestate().getRealestateInstanceType().contains("Land")) {
                    lands.add(makeLand(i));
                } else if (i.getRealestate().getRealestateInstanceType().contains("Households")) {
                    households.add(makeHousehold(i));
                } else if (i.getRealestate().getRealestateInstanceType().contains("Commercial")) {
                    commercials.add(makeCommercial(i));
                }

                String strUpdatedDate = sdf.format(i.getRealestate().getUpdatedAt());
                if (strUpdatedDate.compareTo(strLastDate) > 0)
                    strLastDate = strUpdatedDate;
            }

            mPrefManager.putLastFetchDate(strLastDate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void downloadRequirements() {
        apartmentReqs.clear();
        landReqs.clear();
        rentReqs.clear();
        householdReqs.clear();
        commercialReqs.clear();

        try {
            List<RequirementBase> response = mApi.getRequirements(0L); // TODO: real agent id
            List<Requirement> requirements = new ArrayList<>();

            // TODO : handling response

            mDbUtils.updateRequirements(requirements);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Apartment makeApartment(SearchNearContainer container) {
        Apartment retVal = new Apartment();
        retVal.id = container.getRealestate().getId();
        retVal.cost = container.getRealestateType().getCost();
        retVal.floor = container.getRealestateType().getFloor();
        retVal.floorAll = container.getRealestateType().getFloorAll();
        retVal.idFund = container.getRealestateType().getIdFund();
        retVal.idState = container.getRealestateType().getIdState();
        retVal.idTypeApartment = container.getRealestateType().getIdTypeApartment();
        retVal.idWallMaterial = container.getRealestateType().getIdWallMaterial();
        retVal.kitchenArea = container.getRealestateType().getKitchenArea();
        retVal.livingArea = container.getRealestateType().getLivingArea();
        retVal.totalArea = container.getRealestateType().getTotalArea();
        retVal.firm = container.getRealestate().getIsFirmContactPerson();
        retVal.idAddress = container.getRealestate().getIdAddress();
        return retVal;
    }

    private Land makeLand(SearchNearContainer container) {
        Land retVal = new Land();
        retVal.id = container.getRealestate().getId();
        retVal.cost = container.getRealestateType().getCost();
        retVal.idEntry = container.getRealestateType().getIdEntry();
        retVal.idFurniture = container.getRealestateType().getIdFurniture();
        retVal.idState = container.getRealestateType().getIdState();
        retVal.idWallMaterial = container.getRealestateType().getIdWallMaterial();
        retVal.idAddress = container.getRealestate().getIdAddress();
        retVal.kitchenArea = container.getRealestateType().getKitchenArea();
        retVal.livingArea = container.getRealestateType().getLivingArea();
        retVal.totalArea = container.getRealestateType().getTotalArea();
        retVal.stead = container.getRealestateType().getStead();
        retVal.firm = container.getRealestate().getIsFirmContactPerson();
        return retVal;
    }

    private Rent makeRent(SearchNearContainer container) {
        Rent retVal = new Rent();
        retVal.id = container.getRealestate().getId();
        retVal.firm = container.getRealestate().getIsFirmContactPerson();
        retVal.idAddress = container.getRealestate().getIdAddress();
        retVal.cost = container.getRealestateType().getCost();
        retVal.floor = container.getRealestateType().getFloor();
        retVal.floorAll = container.getRealestateType().getFloorAll();
        retVal.hasPhone = container.getRealestateType().getHasPhone();
        retVal.idComfort = container.getRealestateType().getIdComfort();
        retVal.idFurniture = container.getRealestateType().getIdFurniture();
        retVal.idState = container.getRealestateType().getIdState();
        retVal.idYard = container.getRealestateType().getIdYard();
        retVal.roomCount = container.getRealestateType().getRoomCount();
        retVal.prepayment = container.getRealestateType().getPrepayment();
        retVal.idEntry = container.getRealestateType().getIdEntry();
        retVal.idTypeApartment = container.getRealestateType().getIdTypeApartment();
        //retVal.dateFreed = container.getRealestateType().getDatePuttingHouse(); //TODO: check

        TypeRent[] foo = container.getRealestateType().getTypeRent();
        retVal.idRent = new Long[foo.length];
        for (int i = 0; i < foo.length; i++) {
            retVal.idRent[i] = foo[i].getId();
        }

        return retVal;
    }

    private Commercial makeCommercial(SearchNearContainer container) {
        Commercial retVal = new Commercial();
        retVal.id = container.getRealestate().getId();
        retVal.idAddress = container.getRealestate().getIdAddress();
        retVal.firm = container.getRealestate().getIsFirmContactPerson();
        retVal.hallArea = container.getRealestateType().getHallArea();
        retVal.landArea = container.getRealestateType().getLandArea();
        retVal.totalArea = container.getRealestateType().getTotalArea();
        retVal.rentArea = container.getRealestateType().getRentArea();
        retVal.sellPrice = container.getRealestateType().getSellPrice();
        retVal.sellPriceSquereMeter = container.getRealestateType().getSellPriceSquareMeter();
        retVal.rentPrice = container.getRealestateType().getRentPrice();
        retVal.rentPriceSquareMeter = container.getRealestateType().getRentPriceSquareMeter();

        LiftingEquipment[] foo = container.getRealestateType().getLiftingEquipment();
        retVal.idLiftingEquipments = new Long[foo.length];
        for (int i = 0; i < foo.length; i++) {
            retVal.idLiftingEquipments[i] = foo[i].getId();
        }

        Communication[] bar = container.getRealestateType().getCommunication();
        retVal.idCommunications = new Long[bar.length];
        for (int i = 0; i < bar.length; i++) {
            retVal.idCommunications[i] = bar[i].getId();
        }

        return retVal;
    }

    private Households makeHousehold(SearchNearContainer container) {
        Households retVal = new Households();
        retVal.id = container.getRealestate().getId();
        retVal.cost = container.getRealestateType().getCost();
        retVal.idEntry = container.getRealestateType().getIdEntry();
        retVal.idFurniture = container.getRealestateType().getIdFurniture();
        retVal.idState = container.getRealestateType().getIdState();
        retVal.idWallMaterial = container.getRealestateType().getIdWallMaterial();
        retVal.idAddress = container.getRealestate().getIdAddress();
        retVal.kitchenArea = container.getRealestateType().getKitchenArea();
        retVal.livingArea = container.getRealestateType().getLivingArea();
        retVal.totalArea = container.getRealestateType().getTotalArea();
        retVal.stead = container.getRealestateType().getStead();
        retVal.firm = container.getRealestate().getIsFirmContactPerson();
        return retVal;
    }

    private List<Recommendation> handleDataByType(String type) {
        List<Recommendation> retVal = new ArrayList<>();
        List<RequirementBase> requirements;
        List<RealtyBase> realty;

        switch (type) {
            case RealtyTypes.TYPE_APARTMENT:
                requirements = apartmentReqs;
                realty = apartments;
                break;
            case RealtyTypes.TYPE_COMMERCIAL:
                requirements = commercialReqs;
                realty = commercials;
                break;
            case RealtyTypes.TYPE_RENT:
                requirements = rentReqs;
                realty = rents;
                break;
            case RealtyTypes.TYPE_LAND:
                requirements = landReqs;
                realty = lands;
                break;
            case RealtyTypes.TYPE_HOUSEHOLD:
                requirements = householdReqs;
                realty = households;
                break;
            default:
                return retVal;
        }

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
