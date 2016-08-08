package dpiki.notificator;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import dpiki.notificator.data.RealtyTypes;
import dpiki.notificator.data.Recommendation;
import dpiki.notificator.data.Requirement;
import dpiki.notificator.network.DataFetcher;
import dpiki.notificator.network.DataFetcherApartmentAdapter;
import dpiki.notificator.network.DataFetcherCommercialAdapter;
import dpiki.notificator.network.dataobjects.Apartment;
import dpiki.notificator.network.dataobjects.ApartmentReq;
import dpiki.notificator.network.dataobjects.Commercial;
import dpiki.notificator.network.dataobjects.CommercialReq;
import dpiki.notificator.network.dataobjects.RealtyBase;
import dpiki.notificator.network.dataobjects.RequirementBase;

import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Lenovo on 04.08.2016.
 */
public class DataFetcherTest {

    @Test
    public void testReceiveBothRealtyAndRequirements() {
        DataFetcherApartmentAdapter adapter = mock(DataFetcherApartmentAdapter.class);
        when(adapter.getRealty("2016-01-01 12:00:00")).thenReturn(dataSetApartment1());
        when(adapter.getRequirements(anyInt())).thenReturn(dataSetApartmentReq1());
        when(adapter.getType()).thenCallRealMethod();

        PrefManager manager = mock(PrefManager.class);
        when(manager.getLastFetchDate(adapter.getType()))
                .thenReturn("2016-01-01 12:00:00");

        DatabaseUtils dbUtils = mock(DatabaseUtils.class);
        final Map<Integer, Integer> unread = dataSetUnreadRecommendations1();
        when(dbUtils.getUnreadRecommendationsCount(anyInt(), anyString())).then(
                new Answer<Integer>() {
                    @Override
                    public Integer answer(InvocationOnMock invocation) throws Throwable {
                        Integer id = (Integer) invocation.getArguments()[0];
                        if (!unread.containsKey(id)) {
                            return 0;
                        } else {
                            return unread.get(id);
                        }
                    }
                });

        DataFetcher fetcher = new DataFetcher(manager, dbUtils);
        List<Recommendation> r = fetcher.fetchRequirements(adapter);

        verify(manager).putLastFetchDate(eq(adapter.getType()), eq("2016-01-01 12:00:00"));
        verify(manager).putLastFetchDate(eq(adapter.getType()), eq("2016-01-01 12:30:00"));
        verify(dbUtils).updateRequirements(argThat(new ListMatcher<>(dataSetRequirement1(adapter.getType()))), eq(RealtyTypes.TYPE_APARTMENT));

        assertThat(r, new ListMatcher<>(dataSetRecommendations1(adapter.getType())));
    }

    @Test
    public void testReceiveBothRealtyAndRequirementsCommercial() {
        DataFetcherCommercialAdapter adapter = mock(DataFetcherCommercialAdapter.class);
        when(adapter.getRealty("2016-01-01 12:00:00")).thenReturn(dataSetApartment2());
        when(adapter.getRequirements(anyInt())).thenReturn(dataSetApartmentReq2());
        when(adapter.getType()).thenCallRealMethod();

        PrefManager manager = mock(PrefManager.class);
        when(manager.getLastFetchDate(adapter.getType()))
                .thenReturn("2016-01-01 12:00:00");

        DatabaseUtils dbUtils = mock(DatabaseUtils.class);
        final Map<Integer, Integer> unread = dataSetUnreadRecommendations2();
        when(dbUtils.getUnreadRecommendationsCount(anyInt(), anyString())).then(
                new Answer<Integer>() {
                    @Override
                    public Integer answer(InvocationOnMock invocation) throws Throwable {
                        Integer id = (Integer) invocation.getArguments()[0];
                        if (!unread.containsKey(id)) {
                            return 0;
                        } else {
                            return unread.get(id);
                        }
                    }
                });

        DataFetcher fetcher = new DataFetcher(manager, dbUtils);
        List<Recommendation> r = fetcher.fetchRequirements(adapter);

        String adapterType = adapter.getType();
        verify(manager).putLastFetchDate(eq(adapter.getType()), eq("2016-01-01 12:00:00"));
        verify(manager).putLastFetchDate(eq(adapter.getType()), eq("2016-01-01 12:30:00"));
        verify(dbUtils).updateRequirements(
                argThat(new ListMatcher<>(dataSetRequirement2(adapter.getType()))),
                eq(adapterType));

        assertThat(r, new ListMatcher<>(dataSetRecommendations2(adapter.getType())));
    }

    @Test
    public void testReceivedOnlyRequirements() {
        DataFetcherApartmentAdapter adapter = mock(DataFetcherApartmentAdapter.class);
        when(adapter.getRealty("2016-01-01 12:00:00")).thenReturn(null);
        when(adapter.getRequirements(anyInt())).thenReturn(dataSetApartmentReq1());
        when(adapter.getType()).thenCallRealMethod();

        PrefManager manager = mock(PrefManager.class);
        when(manager.getLastFetchDate(adapter.getType()))
                .thenReturn("2016-01-01 12:00:00");

        DatabaseUtils dbUtils = mock(DatabaseUtils.class);
        final Map<Integer, Integer> unread = dataSetUnreadRecommendations1();
        when(dbUtils.getUnreadRecommendationsCount(anyInt(), anyString())).then(
                new Answer<Integer>() {
                    @Override
                    public Integer answer(InvocationOnMock invocation) throws Throwable {
                        Integer id = (Integer) invocation.getArguments()[0];
                        if (!unread.containsKey(id)) {
                            return 0;
                        } else {
                            return unread.get(id);
                        }
                    }
                });

        DataFetcher fetcher = new DataFetcher(manager, dbUtils);
        List<Recommendation> r = fetcher.fetchRequirements(adapter);

        verify(manager).putLastFetchDate(eq(adapter.getType()), eq("2016-01-01 12:00:00"));
        verify(dbUtils).updateRequirements(argThat(new ListMatcher<>(dataSetRequirement1(adapter.getType()))), eq(RealtyTypes.TYPE_APARTMENT));

        assertThat(r, new ListMatcher<>(new ArrayList<Recommendation>()));
    }

    @Test
    public void testReceivedNothing() {
        DataFetcherApartmentAdapter adapter = mock(DataFetcherApartmentAdapter.class);
        when(adapter.getRealty("2016-01-01 12:00:00")).thenReturn(null);
        when(adapter.getRequirements(anyInt())).thenReturn(null);
        when(adapter.getType()).thenCallRealMethod();

        PrefManager manager = mock(PrefManager.class);
        when(manager.getLastFetchDate(adapter.getType()))
                .thenReturn("2016-01-01 12:00:00");

        DatabaseUtils dbUtils = mock(DatabaseUtils.class);

        DataFetcher fetcher = new DataFetcher(manager, dbUtils);
        List<Recommendation> r = fetcher.fetchRequirements(adapter);

        verify(manager).putLastFetchDate(eq(adapter.getType()), eq("2016-01-01 12:00:00"));
        verify(dbUtils, never()).updateRequirements(anyList(), anyString());
        verify(dbUtils, never()).getUnreadRecommendationsCount(anyInt(), anyString());
        verify(adapter, never()).getRealty(anyString());

        assertThat(r, new ListMatcher<>(new ArrayList<Recommendation>()));
    }

    /**
     * DataSet 1
     *
     * @return
     */

    List<RequirementBase> dataSetApartmentReq1() {
        List<RequirementBase> retVal = new ArrayList<>();
        retVal.add(new ApartmentReq(3));
        retVal.add(new ApartmentReq(4));
        return retVal;
    }

    List<Requirement> dataSetRequirement1(String type) {
        List<Requirement> retVal = new ArrayList<>();
        retVal.add(new Requirement(3, type, 3));
        retVal.add(new Requirement(4, type, 4));
        return retVal;
    }

    Map<Integer, Integer> dataSetUnreadRecommendations1() {
        Map<Integer, Integer> retVal = new TreeMap<>();
        retVal.put(3, 3);
        retVal.put(4, 4);
        return retVal;
    }

    List<RealtyBase> dataSetApartment1() {
        List<RealtyBase> retVal = new ArrayList<>();
        retVal.add(new Apartment(0, "2016-01-01 12:01:00"));
        retVal.add(new Apartment(6, "2016-01-01 12:30:00"));
        return retVal;
    }

    List<Recommendation> dataSetRecommendations1(String type) {
        List<Recommendation> retVal = new ArrayList<>();
        retVal.add(new Recommendation(3, 0, type));
        retVal.add(new Recommendation(3, 6, type));
        retVal.add(new Recommendation(4, 0, type));
        retVal.add(new Recommendation(4, 6, type));
        return retVal;
    }

    /**
     * DataSet 2
     *
     * @param <T>
     */

    List<RequirementBase> dataSetApartmentReq2() {
        List<RequirementBase> retVal = new ArrayList<>();
        retVal.add(new CommercialReq(888));
        retVal.add(new CommercialReq(5));
        retVal.add(new CommercialReq(3));
        retVal.add(new CommercialReq(4));
        return retVal;
    }

    List<Requirement> dataSetRequirement2(String type) {
        List<Requirement> retVal = new ArrayList<>();
        retVal.add(new Requirement(3, type, 3));
        retVal.add(new Requirement(888, type, 15));
        retVal.add(new Requirement(5, type, 6));
        retVal.add(new Requirement(4, type, 4));
        return retVal;
    }

    Map<Integer, Integer> dataSetUnreadRecommendations2() {
        Map<Integer, Integer> retVal = new TreeMap<>();
        retVal.put(3, 3);
        retVal.put(888, 15);
        retVal.put(5, 6);
        retVal.put(4, 4);
        return retVal;
    }

    List<RealtyBase> dataSetApartment2() {
        List<RealtyBase> retVal = new ArrayList<>();
        retVal.add(new Commercial(0, "2016-01-01 12:01:00"));
        retVal.add(new Commercial(6, "2016-01-01 12:05:00"));
        retVal.add(new Commercial(7, "2016-01-01 12:30:00"));
        return retVal;
    }

    List<Recommendation> dataSetRecommendations2(String type) {
        List<Recommendation> retVal = new ArrayList<>();
        retVal.add(new Recommendation(3, 0, type));
        retVal.add(new Recommendation(3, 6, type));
        retVal.add(new Recommendation(3, 7, type));
        retVal.add(new Recommendation(5, 0, type));
        retVal.add(new Recommendation(5, 6, type));
        retVal.add(new Recommendation(5, 7, type));
        retVal.add(new Recommendation(4, 0, type));
        retVal.add(new Recommendation(4, 6, type));
        retVal.add(new Recommendation(4, 7, type));
        retVal.add(new Recommendation(888, 0, type));
        retVal.add(new Recommendation(888, 6, type));
        retVal.add(new Recommendation(888, 7, type));
        return retVal;
    }

}
