package dpiki.notificator;

import android.content.Context;

import org.bouncycastle.crypto.digests.LongDigest;
import org.bouncycastle.ocsp.Req;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;
import java.util.List;

import dpiki.notificator.data.RealtyTypes;
import dpiki.notificator.data.Recommendation;
import dpiki.notificator.data.Requisition;

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

    @Test
    public void testAddReadRecommendations() {

        DatabaseUtils databaseUtils = new DatabaseUtils(context);
        databaseUtils.addRecommendations(dataSetRecommendations());
        Assert.assertThat(databaseUtils.readRecommendations(0L, RealtyTypes.TYPE_APARTMENT),
                new ListMatcher<>(dataSetRealEstate1()));
        Assert.assertThat(databaseUtils.readRecommendations(0L, RealtyTypes.TYPE_RENT),
                new ListMatcher<>(dataSetRealEstate2()));
        Assert.assertThat(databaseUtils.readRecommendations(1L, RealtyTypes.TYPE_RENT),
                new ListMatcher<>(dataSetRealEstate3()));

    }

    @Test
    public void testClearRecommendations() {
        DatabaseUtils databaseUtils = new DatabaseUtils(context);
        databaseUtils.addRecommendations(dataSetRecommendations());

        databaseUtils.clearRecommendations(dataSetRequisitions());
        Assert.assertThat(databaseUtils.readRecommendations(0L, RealtyTypes.TYPE_APARTMENT),
                new ListMatcher<>(dataSetRealEstate1()));
        Assert.assertThat(databaseUtils.readRecommendations(0L, RealtyTypes.TYPE_RENT),
                new ListMatcher<>(dataSetRealEstate2()));
        Assert.assertThat(databaseUtils.readRecommendations(1L, RealtyTypes.TYPE_RENT),
                new ListMatcher<>(new ArrayList<Long>()));

        databaseUtils.clearRecommendations(new ArrayList<Requisition>());
        Assert.assertThat(databaseUtils.readRecommendations(0L, RealtyTypes.TYPE_APARTMENT),
                new ListMatcher<>(new ArrayList<Long>()));
        Assert.assertThat(databaseUtils.readRecommendations(0L, RealtyTypes.TYPE_RENT),
                new ListMatcher<>(new ArrayList<Long>()));
        Assert.assertThat(databaseUtils.readRecommendations(1L, RealtyTypes.TYPE_RENT),
                new ListMatcher<>(new ArrayList<Long>()));
    }

    private List<Recommendation> dataSetRecommendations() {
        List<Recommendation> retVal = new ArrayList<>();
        retVal.add(new Recommendation(0L, 1L, RealtyTypes.TYPE_APARTMENT));
        retVal.add(new Recommendation(0L, 2L, RealtyTypes.TYPE_RENT));
        retVal.add(new Recommendation(1L, 3L, RealtyTypes.TYPE_RENT));
        retVal.add(new Recommendation(1L, 4L, RealtyTypes.TYPE_RENT));
        return retVal;
    }

    private List<Long> dataSetRealEstate1() {
        List<Long> retVal = new ArrayList<>();
        retVal.add(1L);
        return retVal;
    }

    private List<Long> dataSetRealEstate2() {
        List<Long> retVal = new ArrayList<>();
        retVal.add(2L);
        return retVal;
    }

    private List<Long> dataSetRealEstate3() {
        List<Long> retVal = new ArrayList<>();
        retVal.add(3L);
        retVal.add(4L);
        return retVal;
    }

    private List<Requisition> dataSetRequisitions() {
        List<Requisition> retVal = new ArrayList<>();
        Requisition r = new Requisition();
        r.id = 0L;
        r.type = RealtyTypes.TYPE_APARTMENT;
        retVal.add(r);

        r = new Requisition();
        r.id = 0L;
        r.type = RealtyTypes.TYPE_RENT;
        retVal.add(r);
        return retVal;
    }
}
