package dpiki.notificator;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import dpiki.notificator.network.dataobjects.RealtyBase;
import dpiki.notificator.network.dataobjects.RequirementBase;

/**
 * Created by User on 09.08.2016.
 */
public class TestUtils {

    public static void testRange(RealtyBase a, RequirementBase ar, String fieldName, String limit1name, String limit2name) throws NoSuchFieldException, IllegalAccessException {
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

    public static void testRangeInteger(RealtyBase a, RequirementBase ar, String fieldName, String limit1name, String limit2name) throws NoSuchFieldException, IllegalAccessException {
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

    public static void testId(RealtyBase a, RequirementBase ar, String paramName, String filterName) throws NoSuchFieldException, IllegalAccessException {
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

    public static void testInteger(RealtyBase a, RequirementBase ar, String paramName, String filterName) throws NoSuchFieldException, IllegalAccessException {
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
}
