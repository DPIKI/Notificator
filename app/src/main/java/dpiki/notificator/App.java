package dpiki.notificator;

import android.app.Application;
import android.content.Context;

/**
 * Created by Lenovo on 02.08.2016.
 */
public class App extends Application {
    private static AppComponent appComponent;

    public static AppComponent getInstance() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this.getApplicationContext()))
                .build();
    }
}
