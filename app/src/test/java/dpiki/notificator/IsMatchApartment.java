package dpiki.notificator;

import static org.junit.Assert.*;
import static dpiki.notificator.TestUtils.*;
import org.junit.Before;
import org.junit.Test;

import dpiki.notificator.network.dataobjects.Apartment;
import dpiki.notificator.network.dataobjects.ApartmentReq;

/**
 * Created by User on 09.08.2016.
 */
public class IsMatchApartment {
    Apartment a;
    ApartmentReq ar;

    @Before
    public void before() {
        a = new Apartment();
        a.id = 0L;
        a.firm = 1;
        a.cost = 100.0;
        a.floor = 1;
        a.floorAll = 4;
        a.kitchenArea = 10.0;
        a.livingArea = 20.0;
        a.totalArea = 30.0;
        a.idFund = 1L;
        a.idState = 1L;
        a.idTypeApartment = 1L;
        a.idWallMaterial = 1L;
        a.idAddress = 1L;

        ar = new ApartmentReq();
        ar.costFrom = 50.0;
        ar.costTo = 150.0;
        ar.firm = 1;
        ar.floor = 1;
        ar.floorAll = 4;
        ar.idAddress = 1L;
        ar.idTypeApartment = 1L;
        ar.idState = 1L;
        ar.idWallMaterial = 1L;
        ar.idFund = 1L;
        ar.idRequirements = 1L;
        ar.livingAreaFrom = 15.0;
        ar.livingAreaTo = 25.0;
        ar.kitchenAreaFrom = 5.0;
        ar.kitchenAreaTo = 15.0;
        ar.totalAreaFrom = 25.0;
        ar.totalAreaTo = 35.0;
        ar.notFirst = 0;
        ar.notLast = 0;
    }

    @Test
    public void testSuccessMatch() {
        assertEquals(true, a.isMatch(ar));
    }

    @Test
    public void testFirm() {
        ar.firm = 0;
        assertEquals(false, a.isMatch(ar));
        a.firm = 0;
        assertEquals(true, a.isMatch(ar));
        ar.firm = 1;
        assertEquals(false, a.isMatch(ar));
        a.firm = 1;
        assertEquals(true, a.isMatch(ar));
        ar.firm = null;
        assertEquals(true, a.isMatch(ar));
        a.firm = 0;
        assertEquals(true, a.isMatch(ar));
        a.firm = null;
        assertEquals(true, a.isMatch(ar));
        ar.firm = 1;
        assertEquals(true, a.isMatch(ar));

        ar.firm = 1;
        a.firm = 1;
    }

    @Test
    public void testCost() throws NoSuchFieldException, IllegalAccessException {
        testRange(a, ar, "cost", "costFrom", "costTo");
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
    public void testTotalArea() throws NoSuchFieldException, IllegalAccessException {
        testRange(a, ar, "totalArea", "totalAreaFrom", "totalAreaTo");
    }

    @Test
    public void testIdFund() throws NoSuchFieldException, IllegalAccessException {
        testId(a, ar, "idFund", "idFund");
    }

    @Test
    public void testIdTypeApartment() throws NoSuchFieldException, IllegalAccessException {
        testId(a, ar, "idTypeApartment", "idTypeApartment");
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
    public void testIdAddress() throws NoSuchFieldException, IllegalAccessException {
        testId(a, ar, "idAddress", "idAddress");
    }

    @Test
    public void testFloor() {
        // TODO : implement
    }

}
