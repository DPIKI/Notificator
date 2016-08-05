package dpiki.notificator;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import static org.junit.Assert.*;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import dpiki.notificator.data.RealtyTypes;
import dpiki.notificator.data.Recommendation;
import dpiki.notificator.data.Requirement;
import dpiki.notificator.network.DataFetcher;
import dpiki.notificator.network.DataFetcherApartmentAdapter;
import dpiki.notificator.network.dataobjects.Apartment;
import dpiki.notificator.network.dataobjects.ApartmentReq;
import dpiki.notificator.network.dataobjects.RealtyBase;
import dpiki.notificator.network.dataobjects.RequirementBase;

/**
 * Created by Lenovo on 04.08.2016.
 */
//@RunWith(RobolectricGradleTestRunner.class)
//@Config(constants = BuildConfig.class, sdk = 21, manifest = "src/main/AndroidManifest.xml")
public class DataFetcherTest {

    @Test
    public void firstTest() {
    }

    void testFetcher(String dateRead, String dateWrite1, String dateWrite2,
                     List<RequirementBase> reqBases, List<RealtyBase> realtyBases,
                     List<Requirement> requirement, List<Recommendation> recommendations,
                     final Map<Integer, Integer> unread) {
        DataFetcherApartmentAdapter adapter = mock(DataFetcherApartmentAdapter.class);
        when(adapter.getRealty(dateRead)).thenReturn(realtyBases);
        when(adapter.getRequirements(anyInt())).thenReturn(reqBases);
        when(adapter.getType()).thenCallRealMethod();

        PrefManager manager = mock(PrefManager.class);
        when(manager.getLastFetchDate(adapter.getType()))
            .thenReturn(dateRead);

        DatabaseUtils dbUtils = mock(DatabaseUtils.class);
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
        List<Recommendation> r = fetcher.fetch(adapter);

        verify(manager).putLastFetchDate(eq(adapter.getType()), eq("2016-01-01 12:00:00"));
        verify(manager).putLastFetchDate(eq(adapter.getType()), eq("2016-01-01 12:30:00"));
        verify(dbUtils).updateRequirements(argThat(new ListMatcher<>(dataSetRequirement1(adapter.getType()))), eq(RealtyTypes.TYPE_APARTMENT));

        assertThat(r, new ListMatcher<Recommendation>())
    }

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
        retVal.add(new Recommendation(new Requirement(3, type, 3), ))

    }

    class ListMatcher<T> extends BaseMatcher<List<T>> {
        List<T> toCompare;

        public ListMatcher(List<T> toCompare) {
            this.toCompare = toCompare;
        }

        @Override
        public boolean matches(Object item) {
            if (!(item instanceof List))
                return false;


            List<T> testList = (List<T>) item;
            if (testList.size() != toCompare.size())
                return false;

            for (T i : testList) {
                boolean flag = false;
                for (T j : toCompare) {
                    if (j.equals(i)) {
                        flag = true;
                        break;
                    }
                }

                if (!flag)
                    return false;
            }

            return true;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("Invalid list.");
        }
    }
}
