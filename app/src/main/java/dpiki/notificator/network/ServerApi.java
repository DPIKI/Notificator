package dpiki.notificator.network;

import dpiki.notificator.data.ClientResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Lenovo on 25.07.2016.
 */
public interface ServerApi {
    String BASE_URL = "http://192.168.22.63/";

    @GET("get_clients.php")
    Call<ClientResponse> getClients(@Query("agent_id") Integer agentId);

    @GET("get_phones.php")
    Call<PhoneResponse> getPhones(@Query("date") String date);

    @GET("get_phones.php")
    Call<LaptopResponse> getLaptops(@Query("date") String date);
}
