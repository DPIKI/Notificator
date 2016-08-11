package dpiki.notificator;

import org.junit.Test;
import org.mockito.Matchers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import dpiki.notificator.data.RealtyTypes;
import dpiki.notificator.data.Recommendation;
import dpiki.notificator.data.Requirement;
import dpiki.notificator.network.DataFetcher;
import dpiki.notificator.network.ServerApiWrapper;
import dpiki.notificator.network.dataobjects.RequirementContainer;
import dpiki.notificator.network.gson.Realestate;
import dpiki.notificator.network.gson.SearchNearContainer;

/**
 * Created by Lenovo on 11.08.2016.
 */
public class DataFetcherTest {

    @Test
    public void testFetch() throws IOException, ParseException {
        ServerApiWrapper wrapper = mock(ServerApiWrapper.class);
        PrefManager prefManager = mock(PrefManager.class);
        DatabaseUtils databaseUtils = mock(DatabaseUtils.class);

        when(wrapper.getRealty("2016-01-02 00:00:00")).thenReturn(dataSetSearchNearContainer());
        when(wrapper.getRequirements(0L)).thenReturn(dataSetRequirementContainers());

        when(databaseUtils.getUnreadRecommendationsCount(Matchers.anyLong(), Matchers.anyString()))
                .thenReturn(0);

        when(prefManager.getLastFetchDate()).thenReturn("2016-01-02 00:00:00");

        DataFetcher fetcher = new DataFetcher(prefManager, databaseUtils, wrapper);
        List<Recommendation> r = fetcher.fetch();

        verify(prefManager).putLastFetchDate("2016-01-02 00:00:00");
        verify(prefManager).putLastFetchDate("2016-01-02 00:17:04");
        verify(databaseUtils).updateRequirements(argThat(new ListMatcher<>(dataSetRequirements())));
        assertThat(r, new ListMatcher<>(dataSetRecommendations()));
    }

    List<SearchNearContainer> dataSetSearchNearContainer() throws ParseException {
        Realestate realEstate;
        SearchNearContainer snc;
        List<SearchNearContainer> retVal = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        realEstate = new Realestate();
        realEstate.setId(0L);
        realEstate.setUpdatedAt(sdf.parse("2016-01-01 24:00:01"));
        realEstate.setRealestateInstanceType("Apartment");
        snc = new SearchNearContainer();
        snc.setRealestate(realEstate);
        retVal.add(snc);

        realEstate = new Realestate();
        realEstate.setId(1L);
        realEstate.setUpdatedAt(sdf.parse("2016-01-01 24:00:30"));
        realEstate.setRealestateInstanceType("Apartment");
        snc = new SearchNearContainer();
        snc.setRealestate(realEstate);
        retVal.add(snc);

        realEstate = new Realestate();
        realEstate.setId(2L);
        realEstate.setUpdatedAt(sdf.parse("2016-01-01 24:10:01"));
        realEstate.setRealestateInstanceType("Rent");
        snc = new SearchNearContainer();
        snc.setRealestate(realEstate);
        retVal.add(snc);

        realEstate = new Realestate();
        realEstate.setId(3L);
        realEstate.setRealestateInstanceType("Land");
        realEstate.setUpdatedAt(sdf.parse("2016-01-01 24:17:04"));
        snc = new SearchNearContainer();
        snc.setRealestate(realEstate);
        retVal.add(snc);

        return retVal;
    }

    List<RequirementContainer> dataSetRequirementContainers() {
        List<RequirementContainer> retVal = new ArrayList<>();
        retVal.add(new RequirementContainer(0L, RealtyTypes.TYPE_APARTMENT));
        retVal.add(new RequirementContainer(1L, RealtyTypes.TYPE_APARTMENT));
        retVal.add(new RequirementContainer(2L, RealtyTypes.TYPE_RENT));
        retVal.add(new RequirementContainer(2L, RealtyTypes.TYPE_LAND));
        return retVal;
    }

    List<Recommendation> dataSetRecommendations() {
        List<Recommendation> retVal = new ArrayList<>();
        retVal.add(new Recommendation(0L, 0L, RealtyTypes.TYPE_APARTMENT));
        retVal.add(new Recommendation(0L, 1L, RealtyTypes.TYPE_APARTMENT));
        retVal.add(new Recommendation(1L, 0L, RealtyTypes.TYPE_APARTMENT));
        retVal.add(new Recommendation(1L, 1L, RealtyTypes.TYPE_APARTMENT));
        retVal.add(new Recommendation(2L, 2L, RealtyTypes.TYPE_RENT));
        retVal.add(new Recommendation(2L, 3L, RealtyTypes.TYPE_LAND));
        return retVal;
    }

    List<Requirement> dataSetRequirements() {
        List<Requirement> retVal = new ArrayList<>();
        retVal.add(new Requirement(0L, RealtyTypes.TYPE_APARTMENT, 0));
        retVal.add(new Requirement(1L, RealtyTypes.TYPE_APARTMENT, 0));
        retVal.add(new Requirement(2L, RealtyTypes.TYPE_RENT, 0));
        retVal.add(new Requirement(2L, RealtyTypes.TYPE_LAND, 0));
        return retVal;
    }
}
