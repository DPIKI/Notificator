package dpiki.notificator.network;

import com.google.gson.GsonBuilder;

import org.androidannotations.annotations.sharedpreferences.Pref;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import dpiki.notificator.network.dataobjects.ServerResponse;
import dpiki.notificator.network.gson.SearchNearContainer;
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

    public void sendRequest() {
        apartments.clear();
        rents.clear();
        lands.clear();
        households.clear();
        commercials.clear();

        String strLastDate = mPrefManager.getLastFetchDate();
        mPrefManager.putLastFetchDate(strLastDate);

        Call<List<SearchNearContainer>> call = mApi.getRealEstates(strLastDate);
        Response<List<SearchNearContainer>> response;
        try {
            response = call.execute();
            if (response.body() != null) {
                for (SearchNearContainer i : response.body()) {
                    if (i.getRealestate().getRealestateInstanceType().contains("Apartment")) {

                    } else if (i.getRealestate().getRealestateInstanceType().contains("Rent")) {

                    } else if (i.getRealestate().getRealestateInstanceType().contains("Land")) {

                    } else if (i.getRealestate().getRealestateInstanceType().contains("Households")) {

                    } else if (i.getRealestate().getRealestateInstanceType().contains("Commercial")) {

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public List<Apartment> getApartments(String date) {
        Call<ServerResponse<Apartment>> call = mApi.getApartments(date);
        Response<ServerResponse<Apartment>> response;

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        if (response.body() == null)
            return null;

        return response.body().response;
    }

    public List<ApartmentReq> getApartmentRequirements(Integer agentId) {
        Call<ServerResponse<ApartmentReq>> call = mApi.getApartmentRequirements(agentId);
        Response<ServerResponse<ApartmentReq>> response;

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        if (response.body() == null)
            return null;

        return response.body().response;
    }

    public List<Land> getLands(String date) {
        Call<ServerResponse<Land>> call = mApi.getLands(date);
        Response<ServerResponse<Land>> response;

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        if (response.body() == null)
            return null;

        return response.body().response;
    }

    public List<LandReq> getLandRequirements(Integer agentId) {
        Call<ServerResponse<LandReq>> call = mApi.getLandRequirements(agentId);
        Response<ServerResponse<LandReq>> response;

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        if (response.body() == null)
            return null;

        return response.body().response;
    }

    public List<Rent> getRents(String date) {
        Call<ServerResponse<Rent>> call = mApi.getRents(date);
        Response<ServerResponse<Rent>> response;

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        if (response.body() == null)
            return null;

        return response.body().response;
    }

    public List<RentReq> getRentRequirements(Integer agentId) {
        Call<ServerResponse<RentReq>> call = mApi.getRentRequirements(agentId);
        Response<ServerResponse<RentReq>> response;

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        if (response.body() == null)
            return null;

        return response.body().response;
    }

    public List<Households> getHouseholds(String date) {
        Call<ServerResponse<Households>> call = mApi.getHouseholds(date);
        Response<ServerResponse<Households>> response;

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        if (response.body() == null)
            return null;

        return response.body().response;
    }

    public List<HouseholdsReq> getHouseholdRequirements(Integer agentId) {
        Call<ServerResponse<HouseholdsReq>> call = mApi.getHouseholdRequirements(agentId);
        Response<ServerResponse<HouseholdsReq>> response;

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        if (response.body() == null)
            return null;

        return response.body().response;
    }

    public List<Commercial> getCommercials(String date) {
        Call<ServerResponse<Commercial>> call = mApi.getCommercials(date);
        Response<ServerResponse<Commercial>> response;

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        if (response.body() == null)
            return null;

        return response.body().response;
    }

    public List<CommercialReq> getCommercialRequirements(Integer agentId) {
        Call<ServerResponse<CommercialReq>> call = mApi.getCommercialRequirements(agentId);
        Response<ServerResponse<CommercialReq>> response;

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        if (response.body() == null)
            return null;

        return response.body().response;

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

    }

    private Rent makeRent(SearchNearContainer container) {

    }

    private Commercial makeCommercial(SearchNearContainer container) {

    }

    private Households makeHousehold(SearchNearContainer container) {

    }
}
