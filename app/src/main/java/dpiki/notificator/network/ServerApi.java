package dpiki.notificator.network;

import java.util.Date;

import dpiki.notificator.data.ClientResponse;
import dpiki.notificator.data.LaptopResponse;
import dpiki.notificator.data.PhoneResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Lenovo on 25.07.2016.
 */
public interface ServerApi {
    @GET("get_clients.php")
    Call<ClientResponse> getClients(@Query("agent_id") Integer agentId);

    @GET("get_phones.php")
    Call<PhoneResponse> getPhones(@Query("date") Date date);

    @GET("get_phones.php")
    Call<LaptopResponse> getLaptops(@Query("date") Date date);
}
