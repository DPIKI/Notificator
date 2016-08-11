package dpiki.notificator.network;

import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dpiki.notificator.data.RealEstate;
import dpiki.notificator.network.dataobjects.RequirementContainer;
import dpiki.notificator.data.Requisition;
import dpiki.notificator.network.gson.Communication;
import dpiki.notificator.network.gson.LiftingEquipment;
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

    private List<RealEstate> mapRealEstates(List<SearchNearContainer> searchNearContainers) {
        List<RealEstate> realEstates = new ArrayList<>();

        for (SearchNearContainer container : searchNearContainers) {
            RealEstate realEstate = new RealEstate();
            Realestate realestate = container.getRealestate();
            RealestateInfo realEstateInfo = null;
            try {
                realEstateInfo = container.getRealestateType();
            } catch (NullPointerException ignored) {

            }

            realEstate.id = realestate != null ? realestate.getId() : null;
            realEstate.idAddress = realestate != null ? realestate.getIdAddress() : null;
            realEstate.firm = realestate != null ? realestate.getIsFirmContactPerson() : null;
            realEstate.cost = realEstateInfo != null ? realEstateInfo.getCost() : null;
            realEstate.floor = realEstateInfo != null ? realEstateInfo.getFloor() : null;
            realEstate.floorAll = realEstateInfo != null ? realEstateInfo.getFloorAll() : null;
            realEstate.idFund = realEstateInfo != null ? realEstateInfo.getIdFund() : null;
            realEstate.idState = realEstateInfo != null ? realEstateInfo.getIdState() : null;
            realEstate.idTypeApartment = realEstateInfo != null ? realEstateInfo.getIdTypeApartment() : null;
            realEstate.idWallMaterial = realEstateInfo != null ? realEstateInfo.getIdWallMaterial() : null;
            realEstate.kitchenArea = realEstateInfo != null ? realEstateInfo.getKitchenArea() : null;
            realEstate.livingArea = realEstateInfo != null ? realEstateInfo.getLivingArea() : null;
            realEstate.idEntry = realEstateInfo != null ? realEstateInfo.getIdEntry() : null;
            realEstate.idFurniture = realEstateInfo != null ? realEstateInfo.getIdFurniture() : null;
            realEstate.stead = realEstateInfo != null ? realEstateInfo.getStead() : null;
            realEstate.hasPhone = realEstateInfo != null ? realEstateInfo.getHasPhone() : null;
            realEstate.idComfort = realEstateInfo != null ? realEstateInfo.getIdComfort() : null;
            realEstate.idYard = realEstateInfo != null ? realEstateInfo.getIdYard() : null;
            realEstate.roomCount = realEstateInfo != null ? realEstateInfo.getRoomCount() : null;
            realEstate.prepayment = realEstateInfo != null ? realEstateInfo.getPrepayment() : null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            realEstate.dateFreed = realEstateInfo != null ? sdf.format(realEstateInfo.getDatePuttingHouse()) : null;
            realEstate.hallArea = realEstateInfo != null ? realEstateInfo.getHallArea() : null;
            realEstate.landArea = realEstateInfo != null ? realEstateInfo.getLandArea() : null;
            realEstate.totalArea = realEstateInfo != null ? realEstateInfo.getTotalArea() : null;
            realEstate.rentArea = realEstateInfo != null ? realEstateInfo.getRentArea() : null;
            realEstate.sellPrice = realEstateInfo != null ? realEstateInfo.getSellPrice() : null;
            realEstate.sellPriceSquareMeter = realEstateInfo != null ? realEstateInfo.getSellPriceSquareMeter() : null;
            realEstate.rentPrice = realEstateInfo != null ? realEstateInfo.getRentPrice() : null;
            realEstate.rentPriceSquareMeter = realEstateInfo != null ? realEstateInfo.getRentPriceSquareMeter() : null;

            TypeRent[] foo = realEstateInfo != null ? realEstateInfo.getTypeRent() : null;
            if (foo != null) {
                realEstate.idRent = new Long[foo.length];
                for (int i = 0; i < foo.length; i++) {
                    realEstate.idRent[i] = foo[i].getId();
                }
            }
            Communication[] bar = realEstateInfo != null ? realEstateInfo.getCommunication() : null;
            if (bar != null) {
                realEstate.idCommunications = new Long[bar.length];
                for (int i = 0; i < bar.length; i++) {
                    realEstate.idCommunications[i] = bar[i].getId();
                }
            }
            LiftingEquipment[] buz = realEstateInfo != null ? realEstateInfo.getLiftingEquipment() : null;
            if (buz != null) {
                realEstate.idLiftingEquipments = new Long[buz.length];
                for (int i = 0; i < buz.length; i++) {
                    realEstate.idLiftingEquipments[i] = buz[i].getId();
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
