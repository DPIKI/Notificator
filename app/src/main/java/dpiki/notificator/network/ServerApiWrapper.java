package dpiki.notificator.network;

import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dpiki.notificator.data.RealEstate;
import dpiki.notificator.data.RealtyTypes;
import dpiki.notificator.data.Requisition;
import dpiki.notificator.network.dataobjects.RequirementContainer;
import dpiki.notificator.network.gson.Communication;
import dpiki.notificator.network.gson.LiftingEquipment;
import dpiki.notificator.network.gson.Profile;
import dpiki.notificator.network.gson.Realestate;
import dpiki.notificator.network.gson.RealestateInfo;
import dpiki.notificator.network.gson.SearchNearContainer;
import dpiki.notificator.network.gson.TypeRent;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lenovo on 09.08.2016.
 */
public class ServerApiWrapper {
    private ServerApi mApi;

    public ServerApiWrapper() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServerApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()))
                .build();
        mApi = retrofit.create(ServerApi.class);
    }

    public List<RealEstate> getRealEstates(String date) throws IOException {
        Call<List<SearchNearContainer>> call = mApi.getRealEstates(date);
        Response<List<SearchNearContainer>> response;
        response = call.execute();

        if (response.body() == null)
            throw new IOException("Error parsing RealEstate");

        return mapRealEstates(response.body());
    }

    public List<Requisition> getRequisitions(Long agentId) throws IOException {
        Call<List<RequirementContainer>> call = mApi.getRequirements(agentId);
        Response<List<RequirementContainer>> response = call.execute();

        if (response.body() == null)
            throw new IOException("Failed parsing requirements");

        return mapRequisitions(response.body());
    }

    private List<RealEstate> mapRealEstates(List<SearchNearContainer> searchNearContainers) throws IOException {
        List<RealEstate> realEstates = new ArrayList<>();

        for (SearchNearContainer container : searchNearContainers) {
            RealEstate realEstate = new RealEstate();
            Realestate realestate = container.getRealestate();
            RealestateInfo realEstateInfo = null;
            try {
                realEstateInfo = container.getRealestateType();
            } catch (NullPointerException ignored) {

            }

            if (realestate == null) {
                throw new IOException("Field realestate = null");
            }
            if (realEstateInfo == null) {
                throw new IOException("RealEstate field realEstateInfo = null");
            }
            if (realestate.getUpdatedAt() == null) {
                throw new IOException("RealEstate field updatedAt = null");
            }
            if (realestate.getId() == null) {
                throw new IOException("RealEstate field id = null");
            }
            if (realestate.getRealestateInstanceType() == null) {
                throw new IOException("RealEstate field type = null");
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            realEstate.id = realestate.getId();
            if (realestate.getRealestateInstanceType().contains(RealtyTypes.TYPE_APARTMENT)) {
                realEstate.type = RealtyTypes.TYPE_APARTMENT;
            } else if (realestate.getRealestateInstanceType().contains(RealtyTypes.TYPE_COMMERCIAL)) {
                realEstate.type = RealtyTypes.TYPE_COMMERCIAL;
            } else if (realestate.getRealestateInstanceType().contains(RealtyTypes.TYPE_RENT)) {
                realEstate.type = RealtyTypes.TYPE_RENT;
            } else if (realestate.getRealestateInstanceType().contains(RealtyTypes.TYPE_HOUSEHOLD)) {
                realEstate.type = RealtyTypes.TYPE_HOUSEHOLD;
            } else if (realestate.getRealestateInstanceType().contains(RealtyTypes.TYPE_LAND)) {
                realEstate.type = RealtyTypes.TYPE_LAND;
            }
            realEstate.updatedAt = sdf.format(realestate.getUpdatedAt());

            realEstate.idAddress = realestate.getIdAddress();
            realEstate.firm = realestate.getIsFirmContactPerson();
            realEstate.cost = realEstateInfo.getCost();
            realEstate.floor = realEstateInfo.getFloor();
            realEstate.floorAll = realEstateInfo.getFloorAll();
            realEstate.idFund = realEstateInfo.getIdFund();
            realEstate.idState = realEstateInfo.getIdState();
            realEstate.idTypeApartment = realEstateInfo.getIdTypeApartment();
            realEstate.idWallMaterial = realEstateInfo.getIdWallMaterial();
            realEstate.kitchenArea = realEstateInfo.getKitchenArea();
            realEstate.livingArea = realEstateInfo.getLivingArea();
            realEstate.idEntry = realEstateInfo.getIdEntry();
            realEstate.idFurniture = realEstateInfo.getIdFurniture();
            realEstate.stead = realEstateInfo.getStead();
            realEstate.hasPhone = realEstateInfo.getHasPhone();
            realEstate.hasElevator = realEstateInfo.getHasElevator();
            realEstate.idComfort = realEstateInfo.getIdComfort();
            realEstate.idYard = realEstateInfo.getIdYard();
            realEstate.roomCount = realEstateInfo.getRoomCount();
            realEstate.prepayment = realEstateInfo.getPrepayment();
            realEstate.dateFreed = sdf.format(realEstateInfo.getDatePuttingHouse());
            realEstate.hallArea = realEstateInfo.getHallArea();
            realEstate.landArea = realEstateInfo.getLandArea();
            realEstate.totalArea = realEstateInfo.getTotalArea();
            realEstate.rentArea = realEstateInfo.getRentArea();
            realEstate.sellPrice = realEstateInfo.getSellPrice();
            realEstate.sellPriceSquareMeter = realEstateInfo.getSellPriceSquareMeter();
            realEstate.rentPrice = realEstateInfo.getRentPrice();
            realEstate.rentPriceSquareMeter = realEstateInfo.getRentPriceSquareMeter();

            TypeRent[] foo = realEstateInfo.getTypeRent();
            if (foo != null) {
                realEstate.idRents = new Long[foo.length];
                for (int i = 0; i < realEstate.idRents.length; i++) {
                    realEstate.idRents[i] = foo[i].getId();
                }
            }
            Communication[] bar = realEstateInfo.getCommunication();
            if (bar != null) {
                realEstate.idCommunications = new Long[bar.length];
                for (int i = 0; i < realEstate.idCommunications.length; i++) {
                    realEstate.idCommunications[i] = bar[i].getId();
                }
            }
            LiftingEquipment[] buz = realEstateInfo.getLiftingEquipment();
            if (buz != null) {
                realEstate.idLiftingEquipments = new Long[buz.length];
                for (int i = 0; i < realEstate.idLiftingEquipments.length; i++) {
                    realEstate.idLiftingEquipments[i] = buz[i].getId();
                }
            }
            Profile[] bat = realEstateInfo.getProfile();
            if (bat != null) {
                realEstate.idProfiles = new Long[bat.length];
                for (int i = 0; i < realEstate.idProfiles.length; i++) {
                    realEstate.idProfiles[i] = bat[i].getId();
                }
            }
        }

        return realEstates;
    }

    private List<Requisition> mapRequisitions(List<RequirementContainer> requirementContainers) {
        List<Requisition> requisitions = new ArrayList<>();
        //TODO: just do it
        return requisitions;
    }

}
