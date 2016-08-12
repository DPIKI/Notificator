package dpiki.notificator.network;

import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import dpiki.notificator.data.RealEstate;
import dpiki.notificator.data.Requisition;
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
        /*Call<List<SearchNearContainer>> call = mApi.getRealEstates(date);
        Response<List<SearchNearContainer>> response;
        response = call.execute();

        if (response.body() == null)
            throw new IOException("Error parsing realty");

        return response.body();*/
        return null;
    }

    public List<Requisition> getRequisitions(Long agentId) throws IOException {
        /*Call<List<RequirementContainer>> call = mApi.getRequirements(agentId);
        Response<List<RequirementContainer>> response = call.execute();

        if (response.body() == null)
            throw new IOException("Failed parsing requirements");

        return response.body();*/
        return null;
    }
}
