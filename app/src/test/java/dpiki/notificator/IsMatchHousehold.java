package dpiki.notificator;

import org.junit.Test;

import dpiki.notificator.network.dataobjects.Household;
import dpiki.notificator.network.dataobjects.HouseholdReq;

import static dpiki.notificator.TestUtils.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Lenovo on 10.08.2016.
 */
public class IsMatchHousehold {
    private Household household = new Household();
    private HouseholdReq householdReq = new HouseholdReq();

    @Test
    public void testSuccessMatch() {
        household.id = 0L;
        household.idAddress = 1L;
        household.firm = 1;
        household.stead = 10;
        household.cost = 200.0;
        household.totalArea = 100.0;
        household.livingArea = 100.0;
        household.kitchenArea = 100.0;
        household.idState = 1L;
        household.idWallMaterial = 1L;
        household.idEntry = 1L;
        household.idFurniture = 1L;

        householdReq.idAddress = 1L;
        householdReq.firm = 1;
        householdReq.steadFrom = 5;
        householdReq.steadTo = 15;
        householdReq.costFrom = 100.0;
        householdReq.costTo = 300.0;
        householdReq.totalAreaFrom = 50.0;
        householdReq.totalAreaTo = 150.0;
        householdReq.livingAreaFrom = 50.0;
        householdReq.livingAreaTo = 150.0;
        householdReq.kitchenAreaFrom = 50.0;
        householdReq.kitchenAreaTo = 150.0;
        householdReq.idState = 1L;
        householdReq.idWallMaterial = 1L;
        householdReq.idEntry = 1L;
        householdReq.idFurniture = 1L;

        assertEquals(true, household.isMatch(householdReq));

        household = new Household();
        householdReq = new HouseholdReq();
    }

    @Test
    public void testIdAddress() throws NoSuchFieldException, IllegalAccessException {
        testId(household, householdReq, "idAddress", "idAddress");
    }

    @Test
    public void testFirm() throws NoSuchFieldException, IllegalAccessException {
        testInteger(household, householdReq, "firm", "firm");
    }

    @Test
    public void testCost() throws NoSuchFieldException, IllegalAccessException {
        testRange(household, householdReq, "cost", "costFrom", "costTo");
    }

    @Test
    public void testTotalArea() throws NoSuchFieldException, IllegalAccessException {
        testRange(household, householdReq, "totalArea", "totalAreaFrom", "totalAreaTo");
    }

    @Test
    public void testKitchenArea() throws NoSuchFieldException, IllegalAccessException {
        testRange(household, householdReq, "kitchenArea", "kitchenAreaFrom", "kitchenAreaTo");
    }

    @Test
    public void testLivingArea() throws NoSuchFieldException, IllegalAccessException {
        testRange(household, householdReq, "livingArea", "livingAreaFrom", "livingAreaTo");
    }

    @Test
    public void testIdState() throws NoSuchFieldException, IllegalAccessException {
        testId(household, householdReq, "idState", "idState");
    }

    @Test
    public void testIdWallMaterial() throws NoSuchFieldException, IllegalAccessException {
        testId(household, householdReq, "idWallMaterial", "idWallMaterial");
    }

    @Test
    public void testIdEntry() throws NoSuchFieldException, IllegalAccessException {
        testId(household, householdReq, "idEntry", "idEntry");
    }

    @Test
    public void testIdFurniture() throws NoSuchFieldException, IllegalAccessException {
        testId(household, householdReq, "idFurniture", "idFurniture");
    }

    @Test
    public void testStead() throws NoSuchFieldException, IllegalAccessException {
        testRangeInteger(household, householdReq, "stead", "steadFrom", "steadTo");
    }

}
