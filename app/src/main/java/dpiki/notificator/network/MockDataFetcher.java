package dpiki.notificator.network;

import java.util.ArrayList;
import java.util.List;

import dpiki.notificator.DatabaseUtils;
import dpiki.notificator.PrefManager;
import dpiki.notificator.data.Recommendation;

/**
 * Created by prog1 on 03.08.2016.
 */
public class MockDataFetcher extends DataFetcher {

    public MockDataFetcher(PrefManager prefManager, DatabaseUtils utils) {
        super(prefManager, utils);

    }

    @Override
    public List<Recommendation> fetch() {
        return new ArrayList<>();
    }
}
