package dpiki.notificator;

import org.junit.Test;

import dpiki.notificator.network.dataobjects.Commercial;
import dpiki.notificator.network.dataobjects.CommercialReq;

import static dpiki.notificator.TestUtils.testArrayLong;
import static dpiki.notificator.TestUtils.testId;
import static dpiki.notificator.TestUtils.testInteger;
import static dpiki.notificator.TestUtils.testRange;
import static org.junit.Assert.assertEquals;
/**
 * Created by prog1 on 10.08.2016.
 */
public class IsMatchCommercial {
    Commercial commercial = new Commercial();
    CommercialReq commercialReq = new CommercialReq();

    @Test
    public void testSuccessMatch() {
        commercial.id = 0L;
        commercial.idAddress = 1L;
        commercial.firm = 1;
        commercial.totalArea = 100.0;
        commercial.hallArea = 100.0;
        commercial.landArea = 100.0;
        commercial.rentArea = 100.0;
        commercial.sellPrice = 200.0;
        commercial.sellPriceSquareMeter = 200.0;
        commercial.rentPrice = 200.0;
        commercial.rentPriceSquareMeter = 200.0;
        commercial.idLiftingEquipments = new Long[] {0L, 1L, 2L};
        commercial.idCommunications = new Long[] {0L, 78L, 2L};

        commercialReq.idAddress = 1L;
        commercialReq.firm = 1;
        commercialReq.totalAreaFrom = 50.0;
        commercialReq.totalAreaTo = 150.0;
        commercialReq.hallAreaFrom = 50.0;
        commercialReq.hallAreaTo = 150.0;
        commercialReq.landAreaFrom = 50.0;
        commercialReq.landAreaTo = 150.0;
        commercialReq.rentAreaFrom = 50.0;
        commercialReq.rentAreaTo = 150.0;
        commercialReq.sellPriceFrom = 100.0;
        commercialReq.sellPriceTo = 300.0;
        commercialReq.sellPriceSquareMeterFrom = 100.0;
        commercialReq.sellPriceSquareMeterTo = 300.0;
        commercialReq.rentalPriceFrom = 100.0;
        commercialReq.rentalPriceTo = 300.0;
        commercialReq.rentalPriceSquareMeterFrom = 100.0;
        commercialReq.rentalPriceSquareMeterTo = 300.0;
        commercialReq.idLiftingEquipment = 1L;
        commercialReq.idCommunication = 78L;

        assertEquals(true, commercial.isMatch(commercialReq));

        commercial = new Commercial();
        commercialReq = new CommercialReq();
    }

    @Test
    public void testIdAddress() throws NoSuchFieldException, IllegalAccessException {
        testId(commercial, commercialReq, "idAddress", "idAddress");
    }

    @Test
    public void testFirm() throws NoSuchFieldException, IllegalAccessException {
        testInteger(commercial, commercialReq, "firm", "firm");
    }

    @Test
    public void testTotalArea() throws NoSuchFieldException, IllegalAccessException {
        testRange(commercial, commercialReq, "totalArea", "totalAreaFrom", "totalAreaTo");
    }

    @Test
    public void testHallArea() throws NoSuchFieldException, IllegalAccessException {
        testRange(commercial, commercialReq, "hallArea", "hallAreaFrom", "hallAreaTo");
    }

    @Test
    public void testLandArea() throws NoSuchFieldException, IllegalAccessException {
        testRange(commercial, commercialReq, "landArea", "landAreaFrom", "landAreaTo");
    }

    @Test
    public void testRentArea() throws NoSuchFieldException, IllegalAccessException {
        testRange(commercial, commercialReq, "rentArea", "rentAreaFrom", "rentAreaTo");
    }

    @Test
    public void testSellPrice() throws NoSuchFieldException, IllegalAccessException {
        testRange(commercial, commercialReq, "sellPrice", "sellPriceFrom", "sellPriceTo");
    }

    @Test
    public void testSellPriceSquareMeter() throws NoSuchFieldException, IllegalAccessException {
        testRange(commercial, commercialReq, "sellPriceSquareMeter",
                "sellPriceSquareMeterFrom", "sellPriceSquareMeterTo");
    }

    @Test
    public void testRentPrice() throws NoSuchFieldException, IllegalAccessException {
        testRange(commercial, commercialReq, "rentPrice", "rentalPriceFrom", "rentalPriceTo");
    }

    @Test
    public void testRentPriceSquareMeter() throws NoSuchFieldException, IllegalAccessException {
        testRange(commercial, commercialReq, "rentPriceSquareMeter",
                "rentalPriceSquareMeterFrom", "rentalPriceSquareMeterTo");
    }

    @Test
    public void testIdLiftingEquipment() throws NoSuchFieldException, IllegalAccessException {
        testArrayLong(commercial, commercialReq, "idLiftingEquipments", "idLiftingEquipment");
    }

    @Test
    public void testIdCommunication() throws NoSuchFieldException, IllegalAccessException {
        testArrayLong(commercial, commercialReq, "idCommunications", "idCommunication");
    }

}
