package dpiki.notificator;

import android.content.Context;

import dpiki.notificator.network.DataFetcher;
import dpiki.notificator.network.DataFetcherCreator;

/**
 * Created by Lenovo on 20.07.2016.
 */
public class MyFetcherCreator extends DataFetcherCreator {
    @Override
    public DataFetcher<?, ?> createFetcher(Context context) {
        return new MyFetcher(context);
    }
}
