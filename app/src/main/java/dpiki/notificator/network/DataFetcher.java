package dpiki.notificator.network;

import android.content.Context;

import java.util.ArrayList;

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

            if (items.isEmpty())
                return;

            ArrayList<Filter> filters = loadFilters();

            if (filters.isEmpty())
                return;

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

    public class Recommendation {
        public Item i;
        public Filter f;

        public Recommendation(Item i, Filter f) {
            this.i = i;
            this.f = f;
        }
    }
}
