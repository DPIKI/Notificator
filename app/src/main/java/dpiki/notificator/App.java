package dpiki.notificator;

import android.app.Application;

/**
 * Created by Lenovo on 02.08.2016.
 */
public class App extends Application{
    private static AppComponent appComponent;

    public static AppComponent getInstance() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
