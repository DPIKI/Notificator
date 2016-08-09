package dpiki.notificator.network;

import com.google.gson.GsonBuilder;

import org.androidannotations.annotations.sharedpreferences.Pref;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import dpiki.notificator.PrefManager;
import dpiki.notificator.network.dataobjects.Apartment;
import dpiki.notificator.network.dataobjects.ApartmentReq;
import dpiki.notificator.network.dataobjects.Commercial;
import dpiki.notificator.network.dataobjects.CommercialReq;
import dpiki.notificator.network.dataobjects.Households;
import dpiki.notificator.network.dataobjects.HouseholdsReq;
import dpiki.notificator.network.dataobjects.Land;
import dpiki.notificator.network.dataobjects.LandReq;
import dpiki.notificator.network.dataobjects.RealtyBase;
import dpiki.notificator.network.dataobjects.Rent;
import dpiki.notificator.network.dataobjects.RentReq;
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
public class ServerApiWrapper {
    private ServerApi mApi;
    private PrefManager mPrefManager;

    private final List<Apartment> apartments = new ArrayList<>();
    private final List<ApartmentReq> apartmentReqs = new ArrayList<>();
    private final List<Rent> rents = new ArrayList<>();
    private final List<RentReq> rentReqs = new ArrayList<>();
    private final List<Land> lands = new ArrayList<>();
    private final List<LandReq> landReqs = new ArrayList<>();
    private final List<Households> households = new ArrayList<>();
    private final List<HouseholdsReq> householdReqs = new ArrayList<>();
    private final List<Commercial> commercials = new ArrayList<>();
    private final List<CommercialReq> commercialReqs = new ArrayList<>();

    public ServerApiWrapper(PrefManager prefManager) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServerApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()))
                .build();
        this.mApi = retrofit.create(ServerApi.class);
        this.mPrefManager = prefManager;
    }

    public void downloadRealties() {
        apartments.clear();
        rents.clear();
        lands.clear();
        households.clear();
        commercials.clear();

        String strLastDate = mPrefManager.getLastFetchDate();
        mPrefManager.putLastFetchDate(strLastDate);

        try {
            Call<List<SearchNearContainer>> call = mApi.getRealEstates(strLastDate);
            Response<List<SearchNearContainer>> response;
            response = call.execute();

            if (response.body() == null) {
                throw new IOException("Error parsing response");
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            for (SearchNearContainer i : response.body()) {
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

    public List<Apartment> getApartments(String date) {
        return apartments;
    }

    public List<ApartmentReq> getApartmentRequirements(Integer agentId) {
        return apartmentReqs;
    }

    public List<Land> getLands(String date) {
        return lands;
    }

    public List<LandReq> getLandRequirements(Integer agentId) {
        return landReqs;
    }

    public List<Rent> getRents(String date) {
        return rents;
    }

    public List<RentReq> getRentRequirements(Integer agentId) {
        return rentReqs;
    }

    public List<Households> getHouseholds(String date) {
        return households;
    }

    public List<HouseholdsReq> getHouseholdRequirements(Integer agentId) {
        return householdReqs;
    }

    public List<Commercial> getCommercials(String date) {
        return commercials;
    }

    public List<CommercialReq> getCommercialRequirements(Integer agentId) {
        return commercialReqs;
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
}
