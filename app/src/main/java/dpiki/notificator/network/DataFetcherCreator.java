package dpiki.notificator.network;

import android.content.Context;

import java.io.Serializable;

/**
 * Created by Lenovo on 20.07.2016.
 */
public abstract class DataFetcherCreator implements Serializable {
    public abstract DataFetcher<?, ?> createFetcher(Context context);
}
