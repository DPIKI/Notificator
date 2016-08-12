package dpiki.notificator;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import dpiki.notificator.data.RealEstate;
import dpiki.notificator.data.Requisition;


/**
 * Created by User on 09.08.2016.
 */
public class TestUtils {

    public static void testRange(RealEstate a, Requisition ar, String fieldName,
                                 String limit1name, String limit2name)
            throws NoSuchFieldException, IllegalAccessException {
        Field fldTest = a.getClass().getField(fieldName);
        Field fldLimit1 = ar.getClass().getField(limit1name);
        Field fldLimit2 = ar.getClass().getField(limit2name);

        Double oldTest = (Double) fldTest.get(a);
        Double oldLimit1 = (Double) fldLimit1.get(ar);
        Double oldLimit2 = (Double) fldLimit2.get(ar);

         /** when parameter is not specified and at least one limit is specified return true */
        fldTest.set(a, null);
        assertEquals(true, a.isMatch(ar));
        fldLimit2.set(ar, null);
        assertEquals(true, a.isMatch(ar));

        /** when limits are not specified parameter does not matter */
        fldLimit1.set(ar, null);
        assertEquals(true, a.isMatch(ar));
        fldTest.set(a, 100.0);
        assertEquals(true, a.isMatch(ar));

        /** one limit is specified */
        fldLimit1.set(ar, 150.0);
        assertEquals(false, a.isMatch(ar));
        fldLimit1.set(ar, 100.0);
        assertEquals(true, a.isMatch(ar));

        /** another limit is specified */
        fldLimit1.set(ar, null);
        fldLimit2.set(ar, 100.0);
        assertEquals(true, a.isMatch(ar));
        fldLimit2.set(ar, 50.0);
        assertEquals(false, a.isMatch(ar));

        /** both limits are specified */
        fldLimit1.set(ar, 50.0);
        fldLimit2.set(ar, 150.0);
        fldTest.set(a, 20.0);
        assertEquals(false, a.isMatch(ar));
        fldTest.set(a, 50.0);
        assertEquals(true, a.isMatch(ar));
        fldTest.set(a, 100.0);
        assertEquals(true, a.isMatch(ar));
        fldTest.set(a, 150.0);
        assertEquals(true, a.isMatch(ar));
        fldTest.set(a, 151.0);
        assertEquals(false, a.isMatch(ar));

        fldTest.set(a, oldTest);
        fldLimit1.set(ar, oldLimit1);
        fldLimit2.set(ar, oldLimit2);
    }

    public static void testRangeInteger(RealEstate a, Requisition ar, String fieldName,
                                        String limit1name, String limit2name)
            throws NoSuchFieldException, IllegalAccessException {
        Field fldTest = a.getClass().getField(fieldName);
        Field fldLimit1 = ar.getClass().getField(limit1name);
        Field fldLimit2 = ar.getClass().getField(limit2name);

        Integer oldTest = (Integer) fldTest.get(a);
        Integer oldLimit1 = (Integer) fldLimit1.get(ar);
        Integer oldLimit2 = (Integer) fldLimit2.get(ar);

         /** when parameter is not specified and at least one limit is specified return true */
        fldTest.set(a, null);
        assertEquals(true, a.isMatch(ar));
        fldLimit2.set(ar, null);
        assertEquals(true, a.isMatch(ar));

        /** when limits are not specified parameter does not matter */
        fldLimit1.set(ar, null);
        assertEquals(true, a.isMatch(ar));
        fldTest.set(a, 100);
        assertEquals(true, a.isMatch(ar));

        /** one limit is specified */
        fldLimit1.set(ar, 150);
        assertEquals(false, a.isMatch(ar));
        fldLimit1.set(ar, 100);
        assertEquals(true, a.isMatch(ar));

        /** another limit is specified */
        fldLimit1.set(ar, null);
        fldLimit2.set(ar, 100);
        assertEquals(true, a.isMatch(ar));
        fldLimit2.set(ar, 50);
        assertEquals(false, a.isMatch(ar));

        /** both limits are specified */
        fldLimit1.set(ar, 50);
        fldLimit2.set(ar, 150);
        fldTest.set(a, 20);
        assertEquals(false, a.isMatch(ar));
        fldTest.set(a, 50);
        assertEquals(true, a.isMatch(ar));
        fldTest.set(a, 100);
        assertEquals(true, a.isMatch(ar));
        fldTest.set(a, 150);
        assertEquals(true, a.isMatch(ar));
        fldTest.set(a, 151);
        assertEquals(false, a.isMatch(ar));

        fldTest.set(a, oldTest);
        fldLimit1.set(ar, oldLimit1);
        fldLimit2.set(ar, oldLimit2);
    }

    public static void testId(RealEstate a, Requisition ar, String paramName,
                              String filterName)
            throws NoSuchFieldException, IllegalAccessException {
        Field fldParam = a.getClass().getField(paramName);
        Field fldFilter = ar.getClass().getField(filterName);

        Long oldParam = (Long) fldParam.get(a);
        Long oldFilter = (Long) fldFilter.get(ar);

        /** when filter is null always return true **/
        fldFilter.set(ar, null);
        fldParam.set(a, 10L);
        assertEquals(true, a.isMatch(ar));
        fldParam.set(a, 100L);
        assertEquals(true, a.isMatch(ar));
        fldParam.set(a, null);
        assertEquals(true, a.isMatch(ar));

        /**
         *  when filter is specified
         *  return true if param is equal to filter or if param is not specified
         */
        fldFilter.set(ar, 10L);
        fldParam.set(a, null);
        assertEquals(true, a.isMatch(ar));
        fldParam.set(a, 100L);
        assertEquals(false, a.isMatch(ar));
        fldParam.set(a, 10L);
        assertEquals(true, a.isMatch(ar));

        fldParam.set(a, oldParam);
        fldFilter.set(ar, oldFilter);
    }

    public static void testInteger(RealEstate a, Requisition ar, String paramName,
                                   String filterName)
            throws NoSuchFieldException, IllegalAccessException {
        Field fldParam = a.getClass().getField(paramName);
        Field fldFilter = ar.getClass().getField(filterName);

        Integer oldParam = (Integer) fldParam.get(a);
        Integer oldFilter = (Integer) fldFilter.get(ar);

        /** when filter is null always return true **/
        fldFilter.set(ar, null);
        fldParam.set(a, 10);
        assertEquals(true, a.isMatch(ar));
        fldParam.set(a, 100);
        assertEquals(true, a.isMatch(ar));
        fldParam.set(a, null);
        assertEquals(true, a.isMatch(ar));

        /**
         *  when filter is specified
         *  return true if param is equal to filter or if param is not specified
         */
        fldFilter.set(ar, 10);
        fldParam.set(a, null);
        assertEquals(true, a.isMatch(ar));
        fldParam.set(a, 100);
        assertEquals(false, a.isMatch(ar));
        fldParam.set(a, 10);
        assertEquals(true, a.isMatch(ar));

        fldParam.set(a, oldParam);
        fldFilter.set(ar, oldFilter);
    }

    public static void testArrayLong(RealEstate a, Requisition ar, String paramName,
                                     String filterName)
            throws NoSuchFieldException, IllegalAccessException {
        Field fldParam = a.getClass().getField(paramName);
        Field fldFilter = ar.getClass().getField(filterName);

        Long[] oldParam = (Long[]) fldParam.get(a);
        Long oldFilter = (Long) fldFilter.get(ar);

        /** when filter is null always return true **/
        fldFilter.set(ar, null);

        fldParam.set(a, new Long[]{10L, 20L, 30L, 40L, 50L});
        assertEquals(true, a.isMatch(ar));

        fldParam.set(a, new Long[]{});
        assertEquals(true, a.isMatch(ar));

        fldParam.set(a, null);
        assertEquals(true, a.isMatch(ar));

        /**
         * when param is empty
         * return false if filter is specified
         */
        fldFilter.set(ar, 10L);

        fldParam.set(a, new Long[]{10L});
        assertEquals(true, a.isMatch(ar));

        fldParam.set(a, new Long[]{});
        assertEquals(false, a.isMatch(ar));

        fldParam.set(a, new Long[]{20L,10L});
        assertEquals(true, a.isMatch(ar));

        /**
         *  when filter is specified
         *  return true if param contains filter or if param is not specified
         */
        fldFilter.set(ar, 30L);

        fldParam.set(a, new Long[]{10L, 20L, 30L, 40L, 50L});
        assertEquals(true, a.isMatch(ar));

        fldParam.set(a, new Long[]{10L, 20L, 40L, 50L});
        assertEquals(false, a.isMatch(ar));

        fldParam.set(a, new Long[]{10L, 20L, 40L, 50L, 30L});
        assertEquals(true, a.isMatch(ar));

        fldParam.set(a, null);
        assertEquals(true, a.isMatch(ar));

        fldParam.set(a, oldParam);
        fldFilter.set(ar, oldFilter);
    }

    public static void testRangeDate(RealEstate a, Requisition ar, String fieldName,
                                     String lowLimitName, String highLimitName)
            throws NoSuchFieldException, IllegalAccessException {
        Field fldTest = a.getClass().getField(fieldName);
        Field fldLowLimit = ar.getClass().getField(lowLimitName);
        Field fldHighLimit = ar.getClass().getField(highLimitName);

        String oldTest = (String) fldTest.get(a);
        String oldLimit1 = (String) fldLowLimit.get(ar);
        String oldLimit2 = (String) fldHighLimit.get(ar);

        /** when parameter is not specified and at least one limit is specified return true */
        fldTest.set(a, null);
        assertEquals(true, a.isMatch(ar));

        fldHighLimit.set(ar, null);
        assertEquals(true, a.isMatch(ar));

        /** when limits are not specified parameter does not matter */
        fldLowLimit.set(ar, null);
        fldHighLimit.set(ar, null);
        fldTest.set(a, null);
        assertEquals(true, a.isMatch(ar));

        fldTest.set(a, "foo bar");
        assertEquals(true, a.isMatch(ar));

        /** one limit is specified */
        fldTest.set(a, "2000-01-01 12:00:00");

        fldHighLimit.set(ar, null);
        /*lowLimit > param*/
        /*param = 2000-01-01 12:00:00*/
        fldLowLimit.set(ar, "2010-01-01 12:00:00");
        assertEquals(false, a.isMatch(ar));

        /*param = 2000-01-01 12:00:00*/
        /*lowLimit < param*/
        fldLowLimit.set(ar, "1999-01-01 12:00:00");
        assertEquals(true, a.isMatch(ar));

        /*param = 2000-01-01 12:00:00*/
        /*lowLimit < param*/
        fldLowLimit.set(ar, "2000-01-01 11:59:59");
        assertEquals(true, a.isMatch(ar));

        /*param = 2000-01-01 12:00:00*/
        /*lowLimit > param*/
        fldLowLimit.set(ar, "2000-01-01 12:00:01");
        assertEquals(false, a.isMatch(ar));

        fldLowLimit.set(ar, null);
        /*param = 2000-01-01 12:00:00*/
        /*highLimit < param*/
        fldHighLimit.set(ar, "1999-01-01 12:00:00");
        assertEquals(false, a.isMatch(ar));

        /*highLimit > param*/
        fldHighLimit.set(ar, "2001-01-01 12:00:00");
        assertEquals(true, a.isMatch(ar));

        /*highLimit > param*/
        fldHighLimit.set(ar, "2000-01-01 12:00:01");
        assertEquals(true, a.isMatch(ar));

        /*highLimit < param*/
        fldHighLimit.set(ar, "2000-01-01 11:59:59");
        assertEquals(false, a.isMatch(ar));

        /** both limits are specified and param is specified */
        fldTest.set(a, "2005-01-01 12:00:00");

        /*param = 2005-01-01 12:00:00*/
        fldLowLimit.set(ar, "2000-01-01 12:00:00");
        fldHighLimit.set(ar, "2010-01-01 12:00:00");
        assertEquals(true, a.isMatch(ar));

        /*param = 2005-01-01 12:00:00*/
        fldLowLimit.set(ar, "2006-01-01 12:00:00");
        assertEquals(false, a.isMatch(ar));

        /*param = 2005-01-01 12:00:00*/
        fldLowLimit.set(ar, "2000-01-01 12:00:00");
        fldHighLimit.set(ar, "2004-01-01 12:00:00");
        assertEquals(false, a.isMatch(ar));

        /*param = 2005-01-01 12:00:00*/
        fldLowLimit.set(ar, "1998-01-01 12:00:00");
        fldHighLimit.set(ar, "1999-01-01 12:00:00");
        assertEquals(false, a.isMatch(ar));

        /*param = 2005-01-01 12:00:00*/
        fldLowLimit.set(ar, "2005-01-01 11:59:59");
        fldHighLimit.set(ar, "2005-01-01 12:00:01");
        assertEquals(true, a.isMatch(ar));

        fldTest.set(a, oldTest);
        fldLowLimit.set(ar, oldLimit1);
        fldHighLimit.set(ar, oldLimit2);
    }
}
