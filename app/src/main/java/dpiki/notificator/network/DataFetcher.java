package dpiki.notificator.network;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import dpiki.notificator.data.Recommendation;
import dpiki.notificator.data.ServerResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Lenovo on 07.07.2016.
 */
public abstract class DataFetcher<
        Product extends dpiki.notificator.data.Product,
        Client extends dpiki.notificator.data.Client> {
    public static final String PREF_NAME = "DataFetcherPreference";
    public static final String PREF_KEY_LAST_FETCH_DATE = "lastFetchDate";

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    Context mContext;
    String mClassName;

    public DataFetcher(Context context, String className) {
        this.mContext = context;
        this.mClassName = className;
    }

    public void fetch(List<Client> clients, List<Recommendation> r) {
        if (clients.isEmpty())
            return;

        Date currDate = new Date();
        String strCurrDate = sdf.format(currDate);
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, 0);
        String strLastDate = pref.getString(PREF_KEY_LAST_FETCH_DATE + mClassName, strCurrDate);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_KEY_LAST_FETCH_DATE + mClassName, strLastDate);
        editor.apply();

        Call<ServerResponse<Product>> productRequest = getProducts(strLastDate);
        Response<ServerResponse<Product>> productResponse;
        try {
            productResponse = productRequest.execute();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        ServerResponse<Product> products = productResponse.body();
        if (products == null)
            return;

        if (!products.success)
            return;

        if (products.products.isEmpty())
            return;

        for (Product i : products.products) {
            for (Client j : clients) {
                if (isMatch(i, j)) {
                    r.add(new Recommendation(j, i));
                }
            }
        }

        Product lastProduct = Collections.max(products.products, new Comparator<Product>() {
            @Override
            public int compare(Product lhs, Product rhs) {
                return lhs.creationDate.compareTo(rhs.creationDate);
            }
        });

        editor.putString(PREF_KEY_LAST_FETCH_DATE + mClassName, lastProduct.creationDate);
        editor.apply();
    }

    protected abstract Call<ServerResponse<Product>> getProducts(String date);
    protected abstract boolean isMatch(Product product, Client client);
}
