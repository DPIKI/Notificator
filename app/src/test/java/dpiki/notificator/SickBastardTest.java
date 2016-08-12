package dpiki.notificator;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dpiki.notificator.data.RealEstate;
import dpiki.notificator.data.RealtyTypes;
import dpiki.notificator.data.Recommendation;
import dpiki.notificator.data.Requisition;
import dpiki.notificator.network.ServerApiWrapper;
import dpiki.notificator.network.SickBastard;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by prog1 on 12.08.2016.
 */
public class SickBastardTest {
    ServerApiWrapper mServerApiWrapper;
    DatabaseUtils mDbUtils;
    PrefManager mPrefManager;

    @Test
    public void testGetRecommendations() throws IOException {
        mServerApiWrapper = mock(ServerApiWrapper.class);
        mDbUtils = mock(DatabaseUtils.class);
        mPrefManager = mock(PrefManager.class);

        when(mServerApiWrapper.getRealEstates(anyString())).thenReturn(realEstatesDataSet());
        when(mServerApiWrapper.getRequisitions(anyLong())).thenReturn(requisitionsDataSet());
        when(mDbUtils.getUnreadRecommendationsCount(anyLong(), anyString())).thenReturn(0);
        when(mPrefManager.getLastFetchDate()).thenReturn("2000-01-01 12:00:00");

        SickBastard bastard = new SickBastard(mDbUtils, mServerApiWrapper, mPrefManager);
        List<Recommendation> recommendations;
        recommendations = bastard.getRecommendations();

        assertThat(recommendations, new ListMatcher<>(recommendationsDataSet()));
        verify(mPrefManager).putLastFetchDate("2030-12-12 00:00:00");
    }

    private List<RealEstate> realEstatesDataSet() {
        List<RealEstate> realEstates = new ArrayList<>();

        RealEstate r = new RealEstate();
        r.id = 100L;
        r.type = RealtyTypes.TYPE_APARTMENT;
        r.updatedAt = "2000-01-01 10:00:00";
        realEstates.add(r);

        r = new RealEstate();
        r.id = 200L;
        r.type = RealtyTypes.TYPE_APARTMENT;
        r.updatedAt = "2000-01-01 10:00:00";
        realEstates.add(r);

        r = new RealEstate();
        r.id = 1L;
        r.type = RealtyTypes.TYPE_COMMERCIAL;
        r.updatedAt = "2030-12-12 00:00:00";
        realEstates.add(r);

        r = new RealEstate();
        r.id = 1L;
        r.type = RealtyTypes.TYPE_HOUSEHOLD;
        r.updatedAt = "2000-01-01 10:00:00";
        realEstates.add(r);

        return realEstates;
    }

    private List<Requisition> requisitionsDataSet() {
        List<Requisition> requisitions = new ArrayList<>();

        Requisition req = new Requisition();
        req.id = 0L;
        req.type = RealtyTypes.TYPE_APARTMENT;
        req.unreadRecommendationsCount = 0;
        requisitions.add(req);

        req = new Requisition();
        req.id = 0L;
        req.type = RealtyTypes.TYPE_HOUSEHOLD;
        req.unreadRecommendationsCount = 0;
        requisitions.add(req);

        req = new Requisition();
        req.id = 0L;
        req.type = RealtyTypes.TYPE_RENT;
        req.unreadRecommendationsCount = 1;
        requisitions.add(req);

        return requisitions;
    }

    private List<Recommendation> recommendationsDataSet() {
        List<Recommendation> recommendations = new ArrayList<>();

        Recommendation r = new Recommendation(0L, 100L, RealtyTypes.TYPE_APARTMENT);
        recommendations.add(r);

        r = new Recommendation(0L, 200L, RealtyTypes.TYPE_APARTMENT);
        recommendations.add(r);

        r = new Recommendation(0L, 1L, RealtyTypes.TYPE_HOUSEHOLD);
        recommendations.add(r);

        return recommendations;
    }

}
