package dpiki.notificator;

/**
 * Created by Lenovo on 02.08.2016.
 */
public class App {
    private static AppComponent appComponent = DaggerAppComponent.builder().build();

    public static AppComponent getInstance() {
        return appComponent;
    }
}
