package dpiki.notificator;

import org.junit.Before;
import org.junit.Test;

import dpiki.notificator.network.dataobjects.Rent;
import dpiki.notificator.network.dataobjects.RentReq;

import static dpiki.notificator.TestUtils.testArrayLong;
import static dpiki.notificator.TestUtils.testId;
import static dpiki.notificator.TestUtils.testInteger;
import static dpiki.notificator.TestUtils.testRange;
import static dpiki.notificator.TestUtils.testRangeDate;
import static dpiki.notificator.TestUtils.testRangeInteger;
import static org.junit.Assert.assertEquals;

/**
 * Created by prog1 on 10.08.2016.
 */
public class IsMatchRent {
    Rent rent = new Rent();
    RentReq rentReq = new RentReq();

    @Test
    public void testSuccessMatch() {
        rent.id = 0L;
        rent.idAddress = 1L;
        rent.firm = 1;
        rent.cost = 100.0;
        rent.floor = 1;
        rent.floorAll = 4;
        rent.roomCount = 10;
        rent.prepayment = 1;
        rent.hasPhone = 1;
        rent.dateFreed = "2010-01-01 12:00:00";
        rent.idRent = new Long[]{1L, 2L, 3L};
        rent.idEntry = 1L;
        rent.idTypeApartment = 1L;
        rent.idComfort = 1L;
        rent.idFurniture = 1L;
        rent.idYard = 1L;
        rent.idState = 1L;

        rentReq.idRequirements = 1L;
        rentReq.idTypeApartment = 1L;
        rentReq.idAddress = 1L;
        rentReq.costFrom = 50.0;
        rentReq.costTo = 150.0;
        rentReq.firm = 1;
        rentReq.floor = 1;
        rentReq.floorAll = 4;
        rentReq.notFirst = 0;
        rentReq.notLast = 0;
        rentReq.idState = 1L;
        rentReq.roomCountFrom = 2;
        rentReq.roomCountTo = 20;
        rentReq.prepayment = 1;
        rentReq.idComfort = 1L;
        rentReq.idFurniture = 1L;
        rentReq.idYard = 1L;
        rentReq.idEntry = 1L;
        rentReq.idPhone = 1;
        rentReq.dateFreedFrom = "2000-01-01 12:00:00";
        rentReq.dateFreedTo = "2020-01-01 12:00:00";
        rentReq.idRent = 2L;

        assertEquals(true, rent.isMatch(rentReq));

        rent = new Rent();
        rentReq = new RentReq();
    }

    @Test
    public void testIdTypeApartment() throws NoSuchFieldException, IllegalAccessException {
        testId(rent, rentReq, "idTypeApartment", "idTypeApartment");
    }

    @Test
    public void testIdAddress() throws NoSuchFieldException, IllegalAccessException {
        testId(rent, rentReq, "idAddress", "idAddress");
    }

    @Test
    public void testCost() throws NoSuchFieldException, IllegalAccessException {
        testRange(rent, rentReq, "cost", "costFrom", "costTo");
    }

    @Test
    public void testFirm() throws NoSuchFieldException, IllegalAccessException {
        testInteger(rent, rentReq, "firm", "firm");
    }

    @Test
    public void testFloor() throws NoSuchFieldException, IllegalAccessException {
        testInteger(rent, rentReq, "floor", "floor");
    }

    @Test
    public void testFloorAll() throws NoSuchFieldException, IllegalAccessException {
        testInteger(rent, rentReq, "floorAll", "floorAll");
    }

    @Test
    public void testNotFirst() {
        rentReq.notFirst = 1;
        rentReq.floor = null;

        rent.floor = 1;
        assertEquals(false, rent.isMatch(rentReq));

        rent.floor = 2;
        assertEquals(true, rent.isMatch(rentReq));

        rent.floor = null;
        assertEquals(true, rent.isMatch(rentReq));

        rentReq.notFirst = null;
        rent.floor = 1;
        assertEquals(true, rent.isMatch(rentReq));

        rent.floor = 2;
        assertEquals(true, rent.isMatch(rentReq));

        rent.floor = null;
        assertEquals(true, rent.isMatch(rentReq));

        rentReq.notFirst = 0;
        rent.floor = 1;
        rentReq.floor = 1;
    }

    @Test
    public void testNotLast() {
        rentReq.floor = null;
        rentReq.floorAll = null;

        rentReq.notLast = 1;
        rent.floor = 1;
        rent.floorAll = 3;
        assertEquals(true, rent.isMatch(rentReq));

        rent.floor = 3;
        assertEquals(false, rent.isMatch(rentReq));

        rent.floorAll = null;
        assertEquals(true, rent.isMatch(rentReq));

        rent.floor = null;
        rent.floorAll = 3;
        assertEquals(true, rent.isMatch(rentReq));

        rentReq.floor = 1;
        rentReq.floorAll = 1;
        rentReq.notLast = 0;
        rent.floor = 1;
        rent.floorAll = 1;
    }

    @Test
    public void testIdState() throws NoSuchFieldException, IllegalAccessException {
        testId(rent, rentReq, "idState", "idState");
    }

    @Test
    public void testRoomCount() throws NoSuchFieldException, IllegalAccessException {
        testRangeInteger(rent, rentReq, "roomCount", "roomCountFrom", "roomCountTo");
    }

    @Test
    public void testPrepayment() throws NoSuchFieldException, IllegalAccessException {
        testInteger(rent, rentReq, "prepayment", "prepayment");
    }

    @Test
    public void testIdComfort() throws NoSuchFieldException, IllegalAccessException {
        testId(rent, rentReq, "idComfort", "idComfort");
    }

    @Test
    public void testIdFurniture() throws NoSuchFieldException, IllegalAccessException {
        testId(rent, rentReq, "idFurniture", "idFurniture");
    }

    @Test
    public void testIdYard() throws NoSuchFieldException, IllegalAccessException {
        testId(rent, rentReq, "idYard", "idYard");
    }

    @Test
    public void testIdEntry() throws NoSuchFieldException, IllegalAccessException {
        testId(rent, rentReq, "idEntry", "idEntry");
    }

    @Test
    public void testHasPhone() throws NoSuchFieldException, IllegalAccessException {
        testInteger(rent, rentReq, "hasPhone", "idPhone");
    }

    @Test
    public void testDateFreed() throws NoSuchFieldException, IllegalAccessException {
        testRangeDate(rent, rentReq, "dateFreed", "dateFreedFrom", "dateFreedTo");
    }

    @Test
    public void testIdRent() throws NoSuchFieldException, IllegalAccessException {
        testArrayLong(rent, rentReq, "idRent", "idRent");
    }

}
