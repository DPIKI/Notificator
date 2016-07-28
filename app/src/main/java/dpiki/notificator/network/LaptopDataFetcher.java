package dpiki.notificator.network;

import android.content.Context;

import dpiki.notificator.data.ServerResponse;
import dpiki.notificator.data.laptop.Laptop;
import dpiki.notificator.data.laptop.LaptopClient;
import retrofit2.Call;

/**
 * Created by Lenovo on 28.07.2016.
 */
public class LaptopDataFetcher extends DataFetcher<Laptop, LaptopClient> {
    private ServerApi mApi;

    public LaptopDataFetcher(Context context, String className, ServerApi api) {
        super(context, className);
        this.mApi = api;
    }

    @Override
    protected Call<ServerResponse<Laptop>> getProducts(String date) {
        return mApi.getLaptops(date);
    }

    @Override
    protected boolean isMatch(Laptop product, LaptopClient client) {
        return product.param11.equals(client.pref11) &&
                product.param12.equals(client.pref12) &&
                product.param13.equals(client.pref13) &&
                product.param14.equals(client.pref14);
    }
}
