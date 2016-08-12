package dpiki.notificator.ui;

/**
 * Created by Lenovo on 12.08.2016.
 */
public interface IPresenter {
    void onResume();
    void onPause();
    void onItemClicked(long id, String type);
    void onRefreshButtonClicked();
    void onNewRecommendations();
}
