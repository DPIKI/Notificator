package dpiki.notificator;

/**
 * Created by prog1 on 07.07.2016.
 */
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;
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
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        context = Robolectric.buildActivity(MainActivity.class).get().getApplicationContext();
        myFetcher = new MyFetcher(context);
    }

    @Test
    public void isMatch_isCorrect() throws Exception{
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

    @Test
    public void findLastAddedPhone_isCorrect() throws Exception{
        String sDate = "2016-01-01 10:00:00";
        Phone p1 = new Phone(1, "Nokia", "p1", "p2", "p3", sdf.parse(sDate));
        sDate = "2016-01-01 10:00:01";
        Phone p2 = new Phone(2, "Samsung", "p3", "p2", "p4", sdf.parse(sDate));
        sDate = "2016-01-01 10:00:02";
        Phone p3 = new Phone(3, "Lenovo", "p1", "p2", "p3", sdf.parse(sDate));
        sDate = "2016-02-01 10:00:03";
        Phone p4 = new Phone(4, "Acer", "p3", "p5", "p4", sdf.parse(sDate));
        sDate = "2016-01-01 10:00:04";
        Phone p5 = new Phone(5, "Asus", "p3", "p2", "p4", sdf.parse(sDate));

        ArrayList<Phone> phones = new ArrayList<>();
        phones.add(p1);
        phones.add(p2);
        phones.add(p3);
        phones.add(p4);
        phones.add(p5);

        Assert.assertEquals("Date is incorrect",
                p4.getDate().getTime(), myFetcher.findLastAddedPhone(phones).getTime());
    }

    @Test
    public void extractResponse_isCorrect() throws Exception {
        JSONObject jsonObject;
        ArrayList<Phone> phones;
        JSONArray jsonArray;

        //------------------------test_with_success==false------------------------------------------
        jsonObject = new JSONObject();
        jsonObject.put(MyFetcher.JSON_KEY_SUCCESS, false);
        phones = myFetcher.extractResponse(jsonObject);
        Assert.assertEquals("Field success == false -> returned size!= 0", 0, phones.size());
        //------------------------test_with_JSONArray-----------------------------------------------
        jsonArray = new JSONArray();
        jsonObject = new JSONObject();
        jsonObject.put(MyFetcher.JSON_KEY_SUCCESS, true);
        //------------------------phone1------------------------------------------------------------
        String sDate = "2016-01-01 10:00:00";
        Phone phone = new Phone( 1, "Nokia", "param1", "param2", "param3", sdf.parse(sDate));
        phones.add(phone);
        jsonArray.put(getJSONFromPhone(phone));
        //------------------------phone2------------------------------------------------------------
        sDate = "2016-12-12 6:00:00";
        phone = new Phone( 2, "Samsung", "p1", "p2", "p3", sdf.parse(sDate));
        phones.add(phone);
        jsonArray.put(getJSONFromPhone(phone));
        //------------------------------------------------------------------------------------------
        jsonObject.put(MyFetcher.JSON_KEY_PHONES, jsonArray);
        ArrayList<Phone> retList = myFetcher.extractResponse(jsonObject);

        Assert.assertEquals(
                "(Exp)Size:" + phones.size() + " != (Act)Size: " + retList.size(),
                phones.size(), retList.size());

        for (int i = 0; i < phones.size(); i++) {
            Phone expected = phones.get(i);
            Phone actual = retList.get(i);
            Assert.assertEquals(
                    "(Exp)Id: " + expected.getId() + "!= (Act)Id: " + actual.getId()
                    ,expected.getId(), actual.getId());
            Assert.assertEquals(
                    "(Exp)Name: " + expected.getName() + "!= (Act)Name: " + actual.getName()
                    ,expected.getName(), actual.getName());
            Assert.assertEquals(
                    "(Exp)Param1: " + expected.getParam1() + "!= (Act)Param1: " + actual.getParam1()
                    ,expected.getParam1(), actual.getParam1());
            Assert.assertEquals(
                    "(Exp)Param2: " + expected.getParam2() + "!= (Act)Param2: " + actual.getParam2()
                    ,expected.getParam2(), actual.getParam2());
            Assert.assertEquals(
                    "(Exp)Param3: " + expected.getParam3() + "!= (Act)Param3: " + actual.getParam3()
                    ,expected.getParam3(), actual.getParam3());
            Assert.assertEquals(
                    "(Exp)Date: " + expected.getDate() + "!= (Act)Date: " + actual.getDate()
                    ,expected.getDate(), actual.getDate());
        }
    }

    public JSONObject getJSONFromPhone(Phone phone) throws Exception{
        JSONObject phoneJSON = new JSONObject();

        phoneJSON.put(MyFetcher.JSON_KEY_ID, phone.getId());
        phoneJSON.put(MyFetcher.JSON_KEY_NAME, phone.getName());
        phoneJSON.put(MyFetcher.JSON_KEY_PARAM1, phone.getParam1());
        phoneJSON.put(MyFetcher.JSON_KEY_PARAM2, phone.getParam2());
        phoneJSON.put(MyFetcher.JSON_KEY_PARAM3, phone.getParam3());
        phoneJSON.put(MyFetcher.JSON_KEY_CREATION_DATE, sdf.format(phone.getDate()));

        return phoneJSON;
    }
}
