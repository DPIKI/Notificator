package dpiki.notificator;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import dpiki.notificator.data.RealtyTypes;
import dpiki.notificator.data.Recommendation;
import dpiki.notificator.data.Requirement;
import dpiki.notificator.network.DataFetcher;
import dpiki.notificator.network.DataFetcherAdapter;
import dpiki.notificator.network.DataFetcherApartment;
import dpiki.notificator.network.ServerApiWrapper;
import dpiki.notificator.network.dataobjects.Apartment;
import dpiki.notificator.network.dataobjects.ApartmentReq;

/**
 * Created by Lenovo on 04.08.2016.
 */
//@RunWith(RobolectricGradleTestRunner.class)
//@Config(constants = BuildConfig.class, sdk = 21, manifest = "src/main/AndroidManifest.xml")
public class DataFetcherTest {

    @Test
    public void firstTest() {
        PrefManager manager = Mockito.mock(PrefManager.class);
        Mockito.when(manager.getLastFetchDate(DataFetcherApartment.class.getName()))
            .thenReturn("2016-01-01 12:00:00");

        DatabaseUtils dbUtils = Mockito.mock(DatabaseUtils.class);
        Mockito.when(dbUtils.getUnreadRecommendationsCount(0, RealtyTypes.TYPE_APARTMENT)).thenReturn(0);
        Mockito.when(dbUtils.getUnreadRecommendationsCount(1, RealtyTypes.TYPE_APARTMENT)).thenReturn(0);
        Mockito.when(dbUtils.getUnreadRecommendationsCount(2, RealtyTypes.TYPE_APARTMENT)).thenReturn(0);
        Mockito.when(dbUtils.getUnreadRecommendationsCount(3, RealtyTypes.TYPE_APARTMENT)).thenReturn(0);

        DataFetcherAdapter<Apartment, ApartmentReq> adapter = Mockito.mock(DataFetcherApartment.class);
        Mockito.when(adapter.)

        DataFetcher<?, ?> fetcher = new DataFetcherApartment(manager, wrapper, dbUtils);
        List<Recommendation> r = new ArrayList<>();
        fetcher.fetch(r);

        Mockito.verify(manager).putLastFetchDate(Matchers.eq(DataFetcherApartment.class.getName()), Matchers.eq("2016-01-01 12:00:00"));
        Mockito.verify(manager).putLastFetchDate(Matchers.eq(DataFetcherApartment.class.getName()), Matchers.eq("2016-01-01 12:30:00"));
        Mockito.verify(dbUtils).updateRequirements(Matchers.argThat(new ListMatcher<>(dataSetRequirement1())), Matchers.eq(RealtyTypes.TYPE_APARTMENT));
        Mockito.verify(dbUtils, Mockito.atLeast(1)).getUnreadRecommendationsCount(0, RealtyTypes.TYPE_APARTMENT);
        Mockito.verify(dbUtils, Mockito.atLeast(1)).getUnreadRecommendationsCount(1, RealtyTypes.TYPE_APARTMENT);
        Mockito.verify(dbUtils, Mockito.atLeast(1)).getUnreadRecommendationsCount(2, RealtyTypes.TYPE_APARTMENT);
        Mockito.verify(dbUtils, Mockito.atLeast(1)).getUnreadRecommendationsCount(3, RealtyTypes.TYPE_APARTMENT);
        Mockito.verify(dbUtils, Mockito.atLeast(1)).getUnreadRecommendationsCount(4, RealtyTypes.TYPE_APARTMENT);
    }

    List<ApartmentReq> dataSetApartmentReq1() {
        List<ApartmentReq> retVal = new ArrayList<>();
        retVal.add(new ApartmentReq(0));
        retVal.add(new ApartmentReq(2));
        retVal.add(new ApartmentReq(1));
        retVal.add(new ApartmentReq(3));
        retVal.add(new ApartmentReq(4));
        return retVal;
    }

    List<Apartment> dataSetApartment1() {
        List<Apartment> retVal = new ArrayList<>();
        retVal.add(new Apartment(0, "2016-01-01 12:01:00"));
        retVal.add(new Apartment(1, "2016-01-01 12:02:00"));
        retVal.add(new Apartment(2, "2016-01-01 12:04:00"));
        retVal.add(new Apartment(3, "2016-01-01 12:03:00"));
        retVal.add(new Apartment(4, "2016-01-01 12:06:00"));
        retVal.add(new Apartment(5, "2016-01-01 12:06:00"));
        retVal.add(new Apartment(6, "2016-01-01 12:30:00"));
        return retVal;
    }

    List<Requirement> dataSetRequirement1() {
        List<Requirement> retVal = new ArrayList<>();
        retVal.add(new Requirement(0, RealtyTypes.TYPE_APARTMENT, 0));
        retVal.add(new Requirement(1, RealtyTypes.TYPE_APARTMENT, 0));
        retVal.add(new Requirement(2, RealtyTypes.TYPE_APARTMENT, 0));
        retVal.add(new Requirement(3, RealtyTypes.TYPE_APARTMENT, 0));
        retVal.add(new Requirement(4, RealtyTypes.TYPE_APARTMENT, 0));
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
