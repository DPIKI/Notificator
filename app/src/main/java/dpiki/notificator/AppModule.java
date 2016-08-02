package dpiki.notificator;

import android.content.Context;
import android.support.annotation.NonNull;

import org.androidannotations.annotations.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dpiki.notificator.PrefManager;
import dpiki.notificator.network.ServerApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lenovo on 02.08.2016.
 */
@Module
public class AppModule {

    private Context mContext;

    public AppModule(@NonNull Context context) {
        this.mContext = context;
    }

    @Provides
    @NonNull
    @Singleton
    PrefManager providePrefManager(Context context) {
        return new PrefManager(context);
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    ServerApi provideServerApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServerApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ServerApi.class);
    }
}
