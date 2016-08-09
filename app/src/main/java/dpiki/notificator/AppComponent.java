package dpiki.notificator;

import javax.inject.Singleton;

import dagger.Component;
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
}
