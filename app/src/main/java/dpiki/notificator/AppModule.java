package dpiki.notificator;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dpiki.notificator.network.DataFetcher;
import dpiki.notificator.network.MockDataFetcher;

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
    @NonNull
    @Singleton
    Context provideContext() {
        return mContext;
    }

    @Provides
    @NonNull
    @Singleton
    DataFetcher provideServerApi(PrefManager prefManager, DatabaseUtils utils) {
        if(Config.isDebug()){
            return new MockDataFetcher(prefManager, utils);
        } else {
            return new DataFetcher(prefManager, utils);
        }
    }

    @Provides
    @NonNull
    @Singleton
    DatabaseUtils provideDatabaseUtils(Context context) {
        return new DatabaseUtils(context);
    }
}
