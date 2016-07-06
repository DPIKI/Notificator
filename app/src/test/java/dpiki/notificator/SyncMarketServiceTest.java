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

import dpiki.notificator.data.Phone;

/**
 * Created by Witaliy on 06.07.2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 18)
public class SyncMarketServiceTest {
    private SyncMarketService marketService;

    @Before
    public void setUp() throws Exception {
        marketService = Robolectric.buildService(SyncMarketService.class).get();
    }

    @Test
    public void testInit() throws Exception {
        Assert.assertNotNull("marketService == null", marketService);
    }

    @Test
    public void testExtractResponse() throws Exception {
        JSONObject jsonObject;
        ArrayList<Phone> phones;
        JSONArray jsonArray;
        JSONObject phoneJSON;

        jsonObject = new JSONObject();
        jsonObject.put(marketService.JSON_KEY_SUCCESS, false);
        phones = marketService.extractResponse(jsonObject);
        Assert.assertEquals("Field success == false -> " +
                "returned size!= 0",
                0, phones.size());


        String sDate = "2016-01-01 10:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(sDate);
        Phone phone =
                new Phone(
                        1,
                        "Nokia",
                        "param1",
                        "param2",
                        "param3",
                        date);
        phones.add(phone);
        jsonObject = new JSONObject();
        jsonObject.put(marketService.JSON_KEY_SUCCESS, true);
        phoneJSON = new JSONObject();

        phoneJSON.put(marketService.JSON_KEY_ID, phone.getId());
        phoneJSON.put(marketService.JSON_KEY_NAME, phone.getName());
        phoneJSON.put(marketService.JSON_KEY_PARAM1, phone.getParam1());
        phoneJSON.put(marketService.JSON_KEY_PARAM2, phone.getParam2());
        phoneJSON.put(marketService.JSON_KEY_PARAM3, phone.getParam3());
        phoneJSON.put(marketService.JSON_KEY_DATE, sDate);
        jsonArray = new JSONArray();
        jsonArray.put(phoneJSON);
        jsonObject.put(marketService.JSON_KEY_PHONES, jsonArray);
        ArrayList<Phone> arrayList = marketService.extractResponse(jsonObject);

        boolean isEqual = false;
        for (int i = 0; i < phones.size(); i++) {
            if (phones.get(i).getId() == arrayList.get(i).getId())
                if(phones.get(i).getName().equals(arrayList.get(i).getName()))
                    if(phones.get(i).getParam1().equals(arrayList.get(i).getParam1()))
                        if(phones.get(i).getParam2().equals(arrayList.get(i).getParam2()))
                            if(phones.get(i).getParam3().equals(arrayList.get(i).getParam3()))
                                if(phones.get(i).getDate().toString().equals(
                                        arrayList.get(i).getDate().toString())) {
                                    isEqual = true;
                                }
            Assert.assertEquals("phone(id = " + phones.get(i).getId() + ")" +
                    " != jsonPhone", true, isEqual);
            isEqual = false;
        }
    }
}
