package dpiki.notificator;

import org.junit.Test;

import dpiki.notificator.data.RealEstate;
import dpiki.notificator.data.Requisition;

import static dpiki.notificator.TestUtils.testArrayArrayLong;
import static dpiki.notificator.TestUtils.testArrayLong;
import static dpiki.notificator.TestUtils.testId;
import static dpiki.notificator.TestUtils.testInteger;
import static dpiki.notificator.TestUtils.testLongArray;
import static dpiki.notificator.TestUtils.testRange;
import static dpiki.notificator.TestUtils.testRangeDate;
import static dpiki.notificator.TestUtils.testRangeInteger;
import static junit.framework.Assert.assertEquals;

/**
 * Created by prog1 on 12.08.2016.
 */
public class IsMatchTest {
    RealEstate a = new RealEstate();
    Requisition ar = new Requisition();

    @Test
    public void testFirm() throws NoSuchFieldException, IllegalAccessException {
        testInteger(a, ar, "firm", "firm");
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
        testLongArray(a, ar, "idTypeApartment", "idTypeApartments");
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
    public void testFloor() throws NoSuchFieldException, IllegalAccessException {
        testInteger(a, ar, "floor", "floor");
    }

    @Test
    public void testFloorAll() throws NoSuchFieldException, IllegalAccessException {
        testInteger(a, ar, "floorAll", "floorAll");
    }

    @Test
    public void testNotFirst() {
        ar.notFirst = 1;
        ar.floor = null;

        a.floor = 1;
        assertEquals(false, a.isMatch(ar));
        a.floor = 2;
        assertEquals(true, a.isMatch(ar));
        a.floor = null;
        assertEquals(true, a.isMatch(ar));

        ar.notFirst = null;
        a.floor = 1;
        assertEquals(true, a.isMatch(ar));
        a.floor = 2;
        assertEquals(true, a.isMatch(ar));
        a.floor = null;
        assertEquals(true, a.isMatch(ar));

        ar.notFirst = 0;
        a.floor = 1;
        ar.floor = 1;
    }

    @Test
    public void testNotLast() {
        ar.floor = null;
        ar.floorAll = null;

        ar.notLast = 1;
        a.floor = 1;
        a.floorAll = 3;
        assertEquals(true, a.isMatch(ar));
        a.floor = 3;
        assertEquals(false, a.isMatch(ar));
        a.floorAll = null;
        assertEquals(true, a.isMatch(ar));
        a.floor = null;
        a.floorAll = 3;
        assertEquals(true, a.isMatch(ar));

        ar.floor = 1;
        ar.floorAll = 1;
        ar.notLast = 0;
        a.floor = 1;
        a.floorAll = 1;
    }

    @Test
    public void testHallArea() throws NoSuchFieldException, IllegalAccessException {
        testRange(a, ar, "hallArea", "hallAreaFrom", "hallAreaTo");
    }

    @Test
    public void testLandArea() throws NoSuchFieldException, IllegalAccessException {
        testRange(a, ar, "landArea", "landAreaFrom", "landAreaTo");
    }

    @Test
    public void testRentArea() throws NoSuchFieldException, IllegalAccessException {
        testRange(a, ar, "rentArea", "rentAreaFrom", "rentAreaTo");
    }

    @Test
    public void testSellPrice() throws NoSuchFieldException, IllegalAccessException {
        testRange(a, ar, "sellPrice", "sellPriceFrom", "sellPriceTo");
    }

    @Test
    public void testSellPriceSquareMeter() throws NoSuchFieldException, IllegalAccessException {
        testRange(a, ar, "sellPriceSquareMeter",
                "sellPriceSquareMeterFrom", "sellPriceSquareMeterTo");
    }

    @Test
    public void testRentPrice() throws NoSuchFieldException, IllegalAccessException {
        testRange(a, ar, "rentPrice", "rentalPriceFrom", "rentalPriceTo");
    }

    @Test
    public void testRentPriceSquareMeter() throws NoSuchFieldException, IllegalAccessException {
        testRange(a, ar, "rentPriceSquareMeter",
                "rentalPriceSquareMeterFrom", "rentalPriceSquareMeterTo");
    }

    @Test
    public void testIdLiftingEquipment() throws NoSuchFieldException, IllegalAccessException {
        testArrayArrayLong(a, ar, "idLiftingEquipments", "idLiftingEquipments");
    }

    @Test
    public void testIdCommunications() throws NoSuchFieldException, IllegalAccessException {
        testArrayArrayLong(a, ar, "idCommunications", "idCommunications");
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

    @Test
    public void testRoomCount() throws NoSuchFieldException, IllegalAccessException {
        testRangeInteger(a, ar, "roomCount", "roomCountFrom", "roomCountTo");
    }

    @Test
    public void testPrepayment() throws NoSuchFieldException, IllegalAccessException {
        testInteger(a, ar, "prepayment", "prepayment");
    }

    @Test
    public void testIdComfort() throws NoSuchFieldException, IllegalAccessException {
        testId(a, ar, "idComfort", "idComfort");
    }

    @Test
    public void testIdYard() throws NoSuchFieldException, IllegalAccessException {
        testId(a, ar, "idYard", "idYard");
    }

    @Test
    public void testHasPhone() throws NoSuchFieldException, IllegalAccessException {
        testInteger(a, ar, "hasPhone", "idPhone");
    }

    @Test
    public void testDateFreed() throws NoSuchFieldException, IllegalAccessException {
        testRangeDate(a, ar, "dateFreed", "dateFreedFrom", "dateFreedTo");
    }

    @Test
    public void testIdRents() throws NoSuchFieldException, IllegalAccessException {
        testArrayLong(a, ar, "idRents", "idRent");
    }

    @Test
    public void testHasElevator() throws NoSuchFieldException, IllegalAccessException {
        testInteger(a, ar, "hasElevator", "hasElevator");
    }

    @Test
    public void testIdRealEstateState() throws NoSuchFieldException, IllegalAccessException {
        testId(a, ar, "idRealEstateState", "idRealEstateState");
    }

    @Test
    public void testSteadX() throws NoSuchFieldException, IllegalAccessException {
        testRangeInteger(a, ar, "steadX", "steadXFrom", "steadXTo");
    }

    @Test
    public void testSteadY() throws NoSuchFieldException, IllegalAccessException {
        testRangeInteger(a, ar, "steadY", "steadYFrom", "steadYTo");
    }

    @Test
    public void testIdProfiles() throws NoSuchFieldException, IllegalAccessException {
        testArrayArrayLong(a, ar, "idProfiles", "idProfiles");
    }

}
