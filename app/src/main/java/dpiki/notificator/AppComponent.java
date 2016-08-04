package dpiki.notificator;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import dpiki.notificator.network.DataFetcherApartment;
import dpiki.notificator.network.DataFetcherCommercial;
import dpiki.notificator.network.DataFetcherHousehold;
import dpiki.notificator.network.DataFetcherLand;
import dpiki.notificator.network.DataFetcherRent;
import dpiki.notificator.network.ServerApi;
import dpiki.notificator.network.ServerApiWrapper;
import dpiki.notificator.network.SyncMarketService;
import dpiki.notificator.ui.MainActivity;

/**
 * Created by Lenovo on 02.08.2016.
 */
@Component(modules = { AppModule.class })
@Singleton
public interface AppComponent {
    void inject(SyncMarketService utils);
    void inject(MainActivity utils);
    Context provideContext();
    PrefManager providePrefManager();
    ServerApiWrapper provideServerApiWrapper();
}
