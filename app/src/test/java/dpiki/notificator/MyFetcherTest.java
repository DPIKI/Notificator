package dpiki.notificator;

/**
 * Created by prog1 on 07.07.2016.
 */
import android.content.Context;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import dpiki.notificator.data.MarketClient;
import dpiki.notificator.data.Phone;
import dpiki.notificator.network.MyFetcher;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 18)
public class MyFetcherTest{
    private MyFetcher myFetcher;
    private SimpleDateFormat sdf;
    private Context context;

    @Before
    public void setUp() throws Exception {
        sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");

        context = Robolectric.buildActivity(MainActivity.class).get().getApplicationContext();
        myFetcher = new MyFetcher(context);
    }

    @Test
    public void test_isMatch() throws Exception{
        ArrayList<Phone> phones = new ArrayList<>();
        String sDate = "2016-01-01 10:00:00";
        Phone p1 = new Phone(1, "Nokia", "p1", "p2", "p3", sdf.parse(sDate));
        Phone p2 = new Phone(2, "Samsung", "p3", "p2", "p4", sdf.parse(sDate));
        Phone p3 = new Phone(3, "Lenovo", "p1", "p2", "p3", sdf.parse(sDate));
        Phone p4 = new Phone(4, "Acer", "p3", "p5", "p4", sdf.parse(sDate));
        Phone p5 = new Phone(5, "Asus", "p3", "p2", "p4", sdf.parse(sDate));

        ArrayList<MarketClient> marketClients = new ArrayList<>();
        MarketClient client1 = new MarketClient(1, "Antony", "p3", "p2", "p4");
        MarketClient client2 = new MarketClient(2, "Witaliy", "p3", "p5", "p4");
        MarketClient client3 = new MarketClient(3, "Vlad", "p1", "p2", "p3");
        MarketClient client4 = new MarketClient(4, "Denis", "p3", "p5", "p4");

        Assert.assertTrue(myFetcher.isMatch(p1, client3));
        Assert.assertTrue(myFetcher.isMatch(p2, client1));
        Assert.assertTrue(myFetcher.isMatch(p3, client3));
        Assert.assertTrue(myFetcher.isMatch(p4, client2));
        Assert.assertTrue(myFetcher.isMatch(p4, client4));
        Assert.assertTrue(myFetcher.isMatch(p5, client1));

        Assert.assertFalse(myFetcher.isMatch(p1, client1));
        Assert.assertFalse(myFetcher.isMatch(p5, client4));
        Assert.assertFalse(myFetcher.isMatch(p4, client3));
    }


}
