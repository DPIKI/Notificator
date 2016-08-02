package dpiki.notificator;

import javax.inject.Singleton;

import dagger.Component;
import dpiki.notificator.network.DataFetcher;
import dpiki.notificator.network.SyncMarketService;

/**
 * Created by Lenovo on 02.08.2016.
 */
@Component(modules = { AppModule.class })
@Singleton
public interface AppComponent {
    void inject(DataFetcher<?, ?> fetcher);
    void inject(DatabaseUtils utils);
    void inject(SyncMarketService utils);
}
