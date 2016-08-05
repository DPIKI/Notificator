package dpiki.notificator;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
        DataFetcherApartmentAdapter adapter = Mockito.mock(DataFetcherApartmentAdapter.class);
        Mockito.when(adapter.getRealty("2016-01-01 12:00:00")).thenReturn(dataSetApartment1());
        Mockito.when(adapter.getRequirements(Matchers.anyInt())).thenReturn(dataSetApartmentReq1());
        Mockito.when(adapter.getType()).thenCallRealMethod();

        PrefManager manager = Mockito.mock(PrefManager.class);
        Mockito.when(manager.getLastFetchDate(adapter.getType()))
            .thenReturn("2016-01-01 12:00:00");

        final Map<Integer, Integer> unreadRecommendations = dataSetUnreadRecommendations1();
        DatabaseUtils dbUtils = Mockito.mock(DatabaseUtils.class);
        Mockito.when(dbUtils.getUnreadRecommendationsCount(0, adapter.getType())).then(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Integer id = (Integer) invocation.getArguments()[0];
                if (!unreadRecommendations.containsKey(id)) {
                    return 0;
                } else {
                    return unreadRecommendations.get(id);
                }
            }
        });

        DataFetcher fetcher = new DataFetcher(manager, dbUtils);
        fetcher.fetch(adapter);

        Mockito.verify(manager).putLastFetchDate(Matchers.eq(adapter.getType()), Matchers.eq("2016-01-01 12:00:00"));
        Mockito.verify(manager).putLastFetchDate(Matchers.eq(adapter.getType()), Matchers.eq("2016-01-01 12:30:00"));
        Mockito.verify(dbUtils).updateRequirements(Matchers.argThat(new ListMatcher<>(dataSetRequirement1(adapter.getType()))), Matchers.eq(adapter.getType()));
    }

    List<RequirementBase> dataSetApartmentReq1() {
        List<RequirementBase> retVal = new ArrayList<>();
        retVal.add(new ApartmentReq(0));
        retVal.add(new ApartmentReq(2));
        retVal.add(new ApartmentReq(1));
        retVal.add(new ApartmentReq(3));
        retVal.add(new ApartmentReq(4));
        return retVal;
    }

    List<Requirement> dataSetRequirement1(String type) {
        List<Requirement> retVal = new ArrayList<>();
        retVal.add(new Requirement(0, type, 0));
        retVal.add(new Requirement(1, type, 1));
        retVal.add(new Requirement(2, type, 2));
        retVal.add(new Requirement(3, type, 3));
        retVal.add(new Requirement(4, type, 4));
        return retVal;
    }

    Map<Integer, Integer> dataSetUnreadRecommendations1() {
        Map<Integer, Integer> retVal = new TreeMap<>();
        retVal.put(0, 0);
        retVal.put(1, 1);
        retVal.put(2, 2);
        retVal.put(3, 3);
        retVal.put(4, 4);
        return retVal;
    }

    List<RealtyBase> dataSetApartment1() {
        List<RealtyBase> retVal = new ArrayList<>();
        retVal.add(new Apartment(0, "2016-01-01 12:01:00"));
        retVal.add(new Apartment(1, "2016-01-01 12:02:00"));
        retVal.add(new Apartment(2, "2016-01-01 12:04:00"));
        retVal.add(new Apartment(3, "2016-01-01 12:03:00"));
        retVal.add(new Apartment(4, "2016-01-01 12:06:00"));
        retVal.add(new Apartment(5, "2016-01-01 12:06:00"));
        retVal.add(new Apartment(6, "2016-01-01 12:30:00"));
        return retVal;
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
