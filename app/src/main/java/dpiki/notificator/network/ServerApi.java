package dpiki.notificator.network;

import java.util.List;

import dpiki.notificator.network.dataobjects.ApartmentReq;
import dpiki.notificator.network.dataobjects.CommercialReq;
import dpiki.notificator.network.dataobjects.HouseholdsReq;
import dpiki.notificator.network.dataobjects.LandReq;
import dpiki.notificator.network.dataobjects.RentReq;
import dpiki.notificator.network.dataobjects.RequirementBase;
import dpiki.notificator.network.gson.SearchNearContainer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Lenovo on 25.07.2016.
 */
public interface ServerApi {
    String BASE_URL = "http://192.168.137.144/";

    @GET("get_real_estates.php")
    Call<List<SearchNearContainer>> getRealEstates(@Query("date") String date);

    @GET("get_requirements.php")
    Call<List<RequirementBase>> getRequirements(@Query("agent_id") Long agentId);
}
