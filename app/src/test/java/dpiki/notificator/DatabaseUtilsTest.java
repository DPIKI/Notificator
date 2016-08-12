package dpiki.notificator;

import android.content.Context;
import android.provider.ContactsContract;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;
import java.util.List;

import dpiki.notificator.data.RealtyTypes;

/**
 * Created by prog1 on 05.08.2016.
 */
@RunWith(RobolectricTestRunner.class)
public class DatabaseUtilsTest {
    public Context context;

    @Before
    public void init() {
        context = RuntimeEnvironment.application.getApplicationContext();
    }

    @Test
    public void testSetUnsetUnreadRecommendations() {
        DatabaseUtils databaseUtils = new DatabaseUtils(context);

        Assert.assertEquals(databaseUtils.getUnreadRecommendationsCount(0L, RealtyTypes.TYPE_APARTMENT), 0);
        databaseUtils.setUnreadRecommendationsCount(0L, RealtyTypes.TYPE_APARTMENT, 5);
        Assert.assertEquals(databaseUtils.getUnreadRecommendationsCount(0L, RealtyTypes.TYPE_APARTMENT), 5);

        databaseUtils = new DatabaseUtils(context);
        Assert.assertEquals(databaseUtils.getUnreadRecommendationsCount(0L, RealtyTypes.TYPE_APARTMENT), 5);
    }
}
