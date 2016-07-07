package dpiki.notificator;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Lenovo on 07.07.2016.
 */
public abstract class DataFetcher<Item, Filter> {
    public abstract ArrayList<Item> loadItems() throws Exception;
    public abstract ArrayList<Filter> loadFilters() throws Exception;
    public abstract Boolean isMatch(Item i, Filter f);
    public abstract void onNewRecommendations(ArrayList<Recommendation> recommendations) throws Exception;

    public void fetch() {
        try {
            ArrayList<Item> items = loadItems();
            ArrayList<Filter> filters = loadFilters();

            ArrayList<Recommendation> recommendations = new ArrayList<>();

            for (Item i : items) {
                for (Filter f : filters) {
                    if (isMatch(i, f)) {
                        Recommendation r = new Recommendation(i, f);
                        recommendations.add(r);
                    }
                }
            }

            if (!recommendations.isEmpty()) {
                onNewRecommendations(recommendations);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class Recommendation {
        Item i;
        Filter f;

        public Recommendation(Item i, Filter f) {
            this.i = i;
            this.f = f;
        }
    }
}
