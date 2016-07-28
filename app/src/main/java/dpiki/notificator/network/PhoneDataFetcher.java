package dpiki.notificator.network;

import android.content.Context;

import dpiki.notificator.data.ServerResponse;
import dpiki.notificator.data.phone.Phone;
import dpiki.notificator.data.phone.PhoneClient;
import retrofit2.Call;

/**
 * Created by Lenovo on 28.07.2016.
 */
public class PhoneDataFetcher extends DataFetcher<Phone, PhoneClient> {
    private ServerApi mApi;

    public PhoneDataFetcher(Context context, String className, ServerApi api) {
        super(context, className);
        this.mApi = api;
    }

    @Override
    protected Call<ServerResponse<Phone>> getProducts(String date) {
        return mApi.getPhones(date);
    }

    @Override
    protected boolean isMatch(Phone product, PhoneClient client) {
        return product.param1.equals(client.pref1) &&
                product.param2.equals(client.pref2) &&
                product.param3.equals(client.pref3);
    }
}
