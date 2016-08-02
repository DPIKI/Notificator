package dpiki.notificator.network;

import java.io.IOException;
import java.util.List;

import dpiki.notificator.data.RealtyTypes;
import dpiki.notificator.data.Recommendation;
import dpiki.notificator.data.Requirement;
import dpiki.notificator.network.dataobjects.Apartment;
import dpiki.notificator.network.dataobjects.ApartmentReq;
import dpiki.notificator.network.dataobjects.ServerResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Lenovo on 02.08.2016.
 */
public class DataFetcherApartment extends DataFetcher<Apartment, ApartmentReq> {

    public DataFetcherApartment() {
        super(DataFetcherApartment.class.getName());
    }

    @Override
    protected List<ApartmentReq> getRequirements(Integer agentId) {
        Call<ServerResponse<ApartmentReq>> call = api.getApartmentRequirements(agentId);
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

    @Override
    protected List<Apartment> getRealty(String date) {
        Call<ServerResponse<Apartment>> call = api.getApartments(date);
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

    @Override
    protected Recommendation makeRecommendation(ApartmentReq apartmentReq, Apartment apartment) {
        Requirement requirement = new Requirement();
        requirement.id = apartmentReq.id;
        requirement.type = RealtyTypes.TYPE_APARTMENT;
        requirement.unreadRecommendations =
    }

    @Override
    protected boolean isMatch(ApartmentReq apartmentReq, Apartment apartment) {
        return false;
    }

    @Override
    protected String newLastDate(List<Apartment> realty) {
        return null;
    }
}
