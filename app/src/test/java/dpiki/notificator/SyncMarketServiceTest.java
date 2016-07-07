package dpiki.notificator;

import android.app.Service;
import android.provider.ContactsContract;

import org.json.JSONArray;
import org.json.JSONException;
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
import java.util.Date;
import java.util.Iterator;
import java.util.logging.StreamHandler;

import dpiki.notificator.data.MarketClient;
import dpiki.notificator.data.Phone;
import dpiki.notificator.data.Recommendation;

/**
 * Created by Witaliy on 06.07.2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 18)
public class SyncMarketServiceTest {
    private SyncMarketService marketService;
    private SimpleDateFormat sdf;

    @Before
    public void setUp() throws Exception {
        marketService = Robolectric.buildService(SyncMarketService.class).get();
        sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
    }

    @Test
    public void testInit() throws Exception {
        Assert.assertNotNull("marketService == null", marketService);
    }

    @Test
    public void extractResponse_isCorrect() throws Exception {
        JSONObject jsonObject;
        ArrayList<Phone> phones;
        JSONArray jsonArray;

        //------------------------test_with_success==false------------------------------------------
        jsonObject = new JSONObject();
        jsonObject.put(marketService.JSON_KEY_SUCCESS, false);
        phones = marketService.extractResponse(jsonObject);
        Assert.assertEquals("Field success == false -> returned size!= 0", 0, phones.size());
        //------------------------test_with_JSONArray-----------------------------------------------
        jsonArray = new JSONArray();
        jsonObject = new JSONObject();
        jsonObject.put(marketService.JSON_KEY_SUCCESS, true);
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
        jsonObject.put(marketService.JSON_KEY_PHONES, jsonArray);
        ArrayList<Phone> retList = marketService.extractResponse(jsonObject);

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

    @Test
    public void filterNewPhones_isCorrect() throws Exception{
        ArrayList<Phone> phones = new ArrayList<>();
        String sDate = "2016-01-01 10:00:00";
        Phone p1 = new Phone(1, "Nokia", "p1", "p2", "p3", sdf.parse(sDate));
        Phone p2 = new Phone(2, "Samsung", "p3", "p2", "p4", sdf.parse(sDate));
        Phone p3 = new Phone(3, "Lenovo", "p1", "p2", "p3", sdf.parse(sDate));
        Phone p4 = new Phone(4, "Acer", "p3", "p5", "p4", sdf.parse(sDate));
        Phone p5 = new Phone(5, "Asus", "p3", "p2", "p4", sdf.parse(sDate));
        phones.add(p1);
        phones.add(p2);
        phones.add(p3);
        phones.add(p4);
        phones.add(p5);

        ArrayList<MarketClient> marketClients = new ArrayList<>();
        MarketClient client1 = new MarketClient(1, "Antony", "p3", "p2", "p4");
        MarketClient client2 = new MarketClient(2, "Witaliy", "p3", "p5", "p4");
        MarketClient client3 = new MarketClient(3, "Vlad", "p1", "p2", "p3");
        MarketClient client4 = new MarketClient(4, "Denis", "p3", "p5", "p4");
        marketClients.add(client1);
        marketClients.add(client2);
        marketClients.add(client3);
        marketClients.add(client4);



        ArrayList<Recommendation> expectedRecommendations = new ArrayList<>();
        //-------------------------Antony
        expectedRecommendations.add(new Recommendation(client1,p2));
        expectedRecommendations.add(new Recommendation(client1,p5));

        //-------------------------Witaliy
        expectedRecommendations.add(new Recommendation(client2,p4));

        //-------------------------Vlad
        expectedRecommendations.add(new Recommendation(client3,p1));
        expectedRecommendations.add(new Recommendation(client3,p3));

        //-------------------------Denis
        expectedRecommendations.add(new Recommendation(client4,p4));

        ArrayList<Recommendation> actualRecommendations =
                marketService.filterNewPhones(phones, marketClients);

        Assert.assertEquals(
                "(Exp)Size: " + expectedRecommendations.size() + " != " +
                        "(Act)Size: " + actualRecommendations.size(),
                expectedRecommendations.size(), actualRecommendations.size());
        for (int i = 0; i < expectedRecommendations.size(); i++){
            Assert.assertTrue(
                    "(Exp)Recommendation: " + expectedRecommendations.get(i) + " not contains in" +
                            "(Act)Recommendation",
                    actualRecommendations.contains(expectedRecommendations.get(i)));
        }
    }

    public JSONObject getJSONFromPhone(Phone phone) throws Exception{
        JSONObject phoneJSON = new JSONObject();

        phoneJSON.put(SyncMarketService.JSON_KEY_ID, phone.getId());
        phoneJSON.put(SyncMarketService.JSON_KEY_NAME, phone.getName());
        phoneJSON.put(SyncMarketService.JSON_KEY_PARAM1, phone.getParam1());
        phoneJSON.put(SyncMarketService.JSON_KEY_PARAM2, phone.getParam2());
        phoneJSON.put(SyncMarketService.JSON_KEY_PARAM3, phone.getParam3());
        phoneJSON.put(SyncMarketService.JSON_KEY_DATE, sdf.format(phone.getDate()));

        return phoneJSON;
    }

}
