package dpiki.notificator;

import org.junit.Test;

import dpiki.notificator.network.dataobjects.Land;
import dpiki.notificator.network.dataobjects.LandReq;

import static dpiki.notificator.TestUtils.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Lenovo on 10.08.2016.
 */
public class IsMatchLand {
    private Land land = new Land();
    private LandReq landReq = new LandReq();

    @Test
    public void testSuccessMatch() {
        land.id = 0L;
        land.idAddress = 1L;
        land.firm = 1;
        land.stead = 10;
        land.cost = 200.0;
        land.totalArea = 100.0;
        land.livingArea = 100.0;
        land.kitchenArea = 100.0;
        land.idState = 1L;
        land.idWallMaterial = 1L;
        land.idEntry = 1L;
        land.idFurniture = 1L;

        landReq.idAddress = 1L;
        landReq.firm = 1;
        landReq.steadFrom = 5;
        landReq.steadTo = 15;
        landReq.costFrom = 100.0;
        landReq.costTo = 300.0;
        landReq.totalAreaFrom = 50.0;
        landReq.totalAreaTo = 150.0;
        landReq.livingAreaFrom = 50.0;
        landReq.livingAreaTo = 150.0;
        landReq.kitchenAreaFrom = 50.0;
        landReq.kitchenAreaTo = 150.0;
        landReq.idState = 1L;
        landReq.idWallMaterial = 1L;
        landReq.idEntry = 1L;
        landReq.idFurniture = 1L;

        assertEquals(true, land.isMatch(landReq));

        land = new Land();
        landReq = new LandReq();
    }

    @Test
    public void testIdAddress() throws NoSuchFieldException, IllegalAccessException {
        testId(land, landReq, "idAddress", "idAddress");
    }

    @Test
    public void testFirm() throws NoSuchFieldException, IllegalAccessException {
        testInteger(land, landReq, "firm", "firm");
    }

    @Test
    public void testCost() throws NoSuchFieldException, IllegalAccessException {
        testRange(land, landReq, "cost", "costFrom", "costTo");
    }

    @Test
    public void testTotalArea() throws NoSuchFieldException, IllegalAccessException {
        testRange(land, landReq, "totalArea", "totalAreaFrom", "totalAreaTo");
    }

    @Test
    public void testKitchenArea() throws NoSuchFieldException, IllegalAccessException {
        testRange(land, landReq, "kitchenArea", "kitchenAreaFrom", "kitchenAreaTo");
    }

    @Test
    public void testLivingArea() throws NoSuchFieldException, IllegalAccessException {
        testRange(land, landReq, "livingArea", "livingAreaFrom", "livingAreaTo");
    }

    @Test
    public void testIdState() throws NoSuchFieldException, IllegalAccessException {
        testId(land, landReq, "idState", "idState");
    }

    @Test
    public void testIdWallMaterial() throws NoSuchFieldException, IllegalAccessException {
        testId(land, landReq, "idWallMaterial", "idWallMaterial");
    }

    @Test
    public void testIdEntry() throws NoSuchFieldException, IllegalAccessException {
        testId(land, landReq, "idEntry", "idEntry");
    }

    @Test
    public void testIdFurniture() throws NoSuchFieldException, IllegalAccessException {
        testId(land, landReq, "idFurniture", "idFurniture");
    }

    @Test
    public void testStead() throws NoSuchFieldException, IllegalAccessException {
        testRangeInteger(land, landReq, "stead", "steadFrom", "steadTo");
    }
}
