package dpiki.notificator;

import org.bouncycastle.ocsp.Req;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;

import dpiki.notificator.data.RealtyTypes;
import dpiki.notificator.data.Recommendation;
import dpiki.notificator.data.Requirement;
import dpiki.notificator.network.DataFetcher;
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

        /*List<Requirement> r1 = new ArrayList<>();

        Requirement foo = new Requirement();
        foo.id = 0;
        foo.type = "type";
        foo.unreadRecommendations = 777;
        r1.add(foo);

        Requirement bar = new Requirement();
        bar.id = 1;
        bar.type = "type";
        bar.unreadRecommendations = 789;
        r1.add(bar);

        List<Requirement> r2 = new ArrayList<>();

        Requirement bar1 = new Requirement();
        bar1.id = 1;
        bar1.type = "type";
        bar1.unreadRecommendations = 789;
        r2.add(bar1);

        Requirement foo1 = new Requirement();
        foo1.id = 0;
        foo1.type = "type";
        foo1.unreadRecommendations = 777;
        r2.add(foo1);
*/
        PrefManager manager = Mockito.mock(PrefManager.class);
        Mockito.when(manager.getLastFetchDate(DataFetcherApartment.class.getName()))
            .thenReturn("2016-01-01 12:00:00");

        List<ApartmentReq> reqs = new ArrayList<>();
        ApartmentReq apartmentReq = new ApartmentReq();
        apartmentReq.idRequirements = 8;
        reqs.add(apartmentReq);
        apartmentReq = new ApartmentReq();
        apartmentReq.idRequirements = 3;
        reqs.add(apartmentReq);
        apartmentReq = new ApartmentReq();
        apartmentReq.idRequirements = 5;
        reqs.add(apartmentReq);
        apartmentReq = new ApartmentReq();
        apartmentReq.idRequirements = 1;
        reqs.add(apartmentReq);
        apartmentReq = new ApartmentReq();
        apartmentReq.idRequirements = 888;
        reqs.add(apartmentReq);
        List<Apartment> apartments = new ArrayList<>();
        Apartment apartment;
        apartment = new Apartment();
        apartment.id = 0;
        apartment.createdAt = "2016-01-01 12:01:00";
        apartments.add(apartment);
        apartment = new Apartment();
        apartment.id = 1;
        apartment.createdAt = "2016-01-01 12:02:00";
        apartments.add(apartment);
        apartment = new Apartment();
        apartment.id = 2;
        apartment.createdAt = "2016-01-01 12:03:00";
        apartments.add(apartment);
        apartment = new Apartment();
        apartment.id = 3;
        apartment.createdAt = "2016-01-01 12:30:00";
        apartments.add(apartment);
        ServerApiWrapper wrapper = Mockito.mock(ServerApiWrapper.class);
        Mockito.when(wrapper.getApartmentRequirements(0)).thenReturn(reqs);
        Mockito.when(wrapper.getApartments("2016-01-01 12:00:00")).thenReturn(apartments);

        DatabaseUtils dbUtils = Mockito.mock(DatabaseUtils.class);
        List<Requirement> testList = new ArrayList<>();
        Requirement requirement;
        requirement = new Requirement();
        requirement.id = 8;
        requirement.type = RealtyTypes.TYPE_APARTMENT;
        requirement.unreadRecommendations = 0;
        testList.add(requirement);
        requirement = new Requirement();
        requirement.id = 3;
        requirement.type = RealtyTypes.TYPE_APARTMENT;
        requirement.unreadRecommendations = 0;
        testList.add(requirement);
        requirement = new Requirement();
        requirement.id = 5;
        requirement.type = RealtyTypes.TYPE_APARTMENT;
        requirement.unreadRecommendations = 0;
        testList.add(requirement);
        requirement = new Requirement();
        requirement.id = 1;
        requirement.type = RealtyTypes.TYPE_APARTMENT;
        requirement.unreadRecommendations = 0;
        testList.add(requirement);
        requirement = new Requirement();
        requirement.id = 888;
        requirement.type = RealtyTypes.TYPE_APARTMENT;
        requirement.unreadRecommendations = 0;
        testList.add(requirement);

        Mockito.when(dbUtils.getUnreadRecommendationsCount(0, RealtyTypes.TYPE_APARTMENT)).thenReturn(0);
        Mockito.when(dbUtils.getUnreadRecommendationsCount(1, RealtyTypes.TYPE_APARTMENT)).thenReturn(0);
        Mockito.when(dbUtils.getUnreadRecommendationsCount(2, RealtyTypes.TYPE_APARTMENT)).thenReturn(0);
        Mockito.when(dbUtils.getUnreadRecommendationsCount(3, RealtyTypes.TYPE_APARTMENT)).thenReturn(0);

        DataFetcher<?, ?> fetcher = new DataFetcherApartment(manager, wrapper, dbUtils);
        List<Recommendation> r = new ArrayList<>();
        fetcher.fetch(r);

        Mockito.verify(manager).putLastFetchDate(Matchers.eq(DataFetcherApartment.class.getName()), Matchers.eq("2016-01-01 12:00:00"));
        Mockito.verify(manager).putLastFetchDate(Matchers.eq(DataFetcherApartment.class.getName()), Matchers.eq("2016-01-01 12:30:00"));
        Mockito.verify(dbUtils).updateRequirements(Matchers.argThat(new ListMatcher<>(testList)), Matchers.eq(RealtyTypes.TYPE_APARTMENT));
        Mockito.verify(dbUtils, Mockito.times(5)).getUnreadRecommendationsCount(8, RealtyTypes.TYPE_APARTMENT);
        Mockito.verify(dbUtils, Mockito.times(5)).getUnreadRecommendationsCount(3, RealtyTypes.TYPE_APARTMENT);
        Mockito.verify(dbUtils, Mockito.times(5)).getUnreadRecommendationsCount(5, RealtyTypes.TYPE_APARTMENT);
        Mockito.verify(dbUtils, Mockito.times(5)).getUnreadRecommendationsCount(1, RealtyTypes.TYPE_APARTMENT);
        Mockito.verify(dbUtils, Mockito.times(5)).getUnreadRecommendationsCount(888, RealtyTypes.TYPE_APARTMENT);
/*        if (new ListMatcher<>(r1).matches(r2)) {
            throw new NullPointerException("Normas");
        } else {
            throw new NullPointerException("Xuyovo");
        }*/

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
