package dpiki.notificator.network;

import dpiki.notificator.data.ClientResponse;
import dpiki.notificator.data.ServerResponse;
import dpiki.notificator.data.laptop.Laptop;
import dpiki.notificator.data.phone.Phone;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Lenovo on 25.07.2016.
 */
public interface ServerApi {
    String BASE_URL = "http://192.168.137.144/";

    @GET("get_clients.php")
    Call<ClientResponse> getClients(@Query("agent_id") Integer agentId);

    @GET("get_phones.php")
    Call<ServerResponse<Phone>> getPhones(@Query("date") String date);

    @GET("get_laptops.php")
    Call<ServerResponse<Laptop>> getLaptops(@Query("date") String date);
}
