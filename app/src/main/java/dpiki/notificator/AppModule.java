package dpiki.notificator;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dpiki.notificator.network.ServerApiWrapper;
import dpiki.notificator.network.SickBastard;

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
    SickBastard provideSickBastard(PrefManager prefManager, DatabaseUtils utils, ServerApiWrapper wrapper) {
        return new SickBastard(utils, wrapper, prefManager);
    }

    @Provides
    @NonNull
    @Singleton
    ServerApiWrapper provideServerApiWrapper() {
        return new ServerApiWrapper();
    }

    @Provides
    @NonNull
    @Singleton
    DatabaseUtils provideDatabaseUtils(Context context) {
        return new DatabaseUtils(context);
    }
}
