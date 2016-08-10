package dpiki.notificator;

import org.junit.Test;

import dpiki.notificator.network.dataobjects.Households;
import dpiki.notificator.network.dataobjects.HouseholdsReq;

import static dpiki.notificator.TestUtils.*;

/**
 * Created by Lenovo on 10.08.2016.
 */
public class IsMatchHousehold {
    private Households a = new Households();
    private HouseholdsReq ar = new HouseholdsReq();

    @Test
    public void testIdAddress() throws NoSuchFieldException, IllegalAccessException {
        testId(a, ar, "idAddress", "idAddress");
    }

    @Test
    public void testFirm() throws NoSuchFieldException, IllegalAccessException {
        testInteger(a, ar, "firm", "firm");
    }

    @Test
    public void testCost() throws NoSuchFieldException, IllegalAccessException {
        testRange(a, ar, "cost", "costFrom", "costTo");
    }

    @Test
    public void testTotalArea() throws NoSuchFieldException, IllegalAccessException {
        testRange(a, ar, "totalArea", "totalAreaFrom", "totalAreaTo");
    }

    @Test
    public void testKitchenArea() throws NoSuchFieldException, IllegalAccessException {
        testRange(a, ar, "kitchenArea", "kitchenAreaFrom", "kitchenAreaTo");
    }

    @Test
    public void testLivingArea() throws NoSuchFieldException, IllegalAccessException {
        testRange(a, ar, "livingArea", "livingAreaFrom", "livingAreaTo");
    }

    @Test
    public void testIdState() throws NoSuchFieldException, IllegalAccessException {
        testId(a, ar, "idState", "idState");
    }

    @Test
    public void testIdWallMaterial() throws NoSuchFieldException, IllegalAccessException {
        testId(a, ar, "idWallMaterial", "idWallMaterial");
    }

    @Test
    public void testIdEntry() throws NoSuchFieldException, IllegalAccessException {
        testId(a, ar, "idEntry", "idEntry");
    }

    @Test
    public void testIdFurniture() throws NoSuchFieldException, IllegalAccessException {
        testId(a, ar, "idFurniture", "idFurniture");
    }

    @Test
    public void testStead() throws NoSuchFieldException, IllegalAccessException {
        testRangeInteger(a, ar, "stead", "steadFrom", "steadTo");
    }
}
