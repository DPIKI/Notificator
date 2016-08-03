package dpiki.notificator;

import javax.inject.Singleton;

import dagger.Component;
import dpiki.notificator.network.DataFetcher;
import dpiki.notificator.network.DataFetcherApartment;
import dpiki.notificator.network.DataFetcherCommercial;
import dpiki.notificator.network.DataFetcherHousehold;
import dpiki.notificator.network.DataFetcherLand;
import dpiki.notificator.network.DataFetcherRent;
import dpiki.notificator.network.SyncMarketService;

/**
 * Created by Lenovo on 02.08.2016.
 */
@Component(modules = { AppModule.class })
@Singleton
public interface AppComponent {
    void inject(DataFetcherApartment fetcher);
    void inject(DataFetcherRent fetcher);
    void inject(DataFetcherHousehold fetcher);
    void inject(DataFetcherCommercial fetcher);
    void inject(DataFetcherLand fetcher);
    void inject(SyncMarketService utils);
    void inject(MainActivity utils);
}
