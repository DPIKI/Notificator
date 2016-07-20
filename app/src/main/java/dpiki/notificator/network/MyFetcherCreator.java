package dpiki.notificator.network;

import android.content.Context;

/**
 * Created by Lenovo on 20.07.2016.
 */
public class MyFetcherCreator extends DataFetcherCreator {
    @Override
    public DataFetcher<?, ?> createFetcher(Context context) {
        return new MyFetcher(context);
    }
}
