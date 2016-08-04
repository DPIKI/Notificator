package dpiki.notificator.network;

import java.io.IOException;
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
import dpiki.notificator.network.dataobjects.ServerResponse;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lenovo on 03.08.2016.
 */
public class ServerApiWrapper {

    private ServerApi mApi;

    public ServerApiWrapper() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServerApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.mApi = retrofit.create(ServerApi.class);
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
}
