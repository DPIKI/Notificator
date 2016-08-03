package dpiki.notificator.network;

import android.provider.ContactsContract;

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
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Lenovo on 25.07.2016.
 */
public interface ServerApi {
    String BASE_URL = "http://192.168.137.144/";

    @GET("get_apartments.php")
    Call<ServerResponse<Apartment>> getApartments(@Query("date") String date);

    @GET("get_apartment_requirements.php")
    Call<ServerResponse<ApartmentReq>> getApartmentRequirements(@Query("agent_id") Integer agentId);

    @GET("get_lands.php")
    Call<ServerResponse<Land>> getLands(@Query("date") String date);

    @GET("get_lands_requirements.php")
    Call<ServerResponse<LandReq>> getLandRequirements(@Query("agent_id") Integer agentId);

    @GET("get_rent.php")
    Call<ServerResponse<Rent>> getRents(@Query("date") String date);

    @GET("get_rent_requirements.php")
    Call<ServerResponse<RentReq>> getRentRequirements(@Query("agent_id") Integer agentId);

    @GET("get_households.php")
    Call<ServerResponse<Households>> getHouseholds(@Query("date") String date);

    @GET("get_household_requirements.php")
    Call<ServerResponse<HouseholdsReq>> getHouseholdRequirements(@Query("agent_id") Integer agentId);

    @GET("get_commercials.php")
    Call<ServerResponse<Commercial>> getCommercials(@Query("date") String date);

    @GET("get_commercial_requirements.php")
    Call<ServerResponse<CommercialReq>> getCommercialRequirements(@Query("agent_id") Integer agentId);
}
