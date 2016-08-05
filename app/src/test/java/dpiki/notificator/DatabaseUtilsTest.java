package dpiki.notificator;

import android.content.Context;
import android.content.res.AssetFileDescriptor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;
import java.util.List;

import dpiki.notificator.data.Realty;
import dpiki.notificator.data.RealtyTypes;
import dpiki.notificator.data.Recommendation;
import dpiki.notificator.data.Requirement;
import dpiki.notificator.network.SyncMarketService;

/**
 * Created by prog1 on 05.08.2016.
 */
@RunWith(RobolectricTestRunner.class)
public class DatabaseUtilsTest {
    public Context context;
    public DatabaseUtils databaseUtils;

    @Before
    public void init(){
        context = RuntimeEnvironment.application.getApplicationContext();
        databaseUtils = new DatabaseUtils(context);
    }

    @Test
    public void test(){
        List<Requirement> listRequirements1 = dataSetRequirements1();
        List<Recommendation> listRecommendation = dataSetRecommendations1();

        databaseUtils.updateRequirements(listRequirements1, RealtyTypes.TYPE_APARTMENT);
        databaseUtils.addRecommendations(listRecommendation);
        listRequirements1.remove(0);
        listRequirements1.remove(0);
        databaseUtils.updateRequirements(listRequirements1, RealtyTypes.TYPE_APARTMENT);

        List<Integer> list = databaseUtils.readRecommendation(0, RealtyTypes.TYPE_APARTMENT);
        Assert.assertThat(list, new ListMatcher<>(new ArrayList<Integer>()));

        list = databaseUtils.readRecommendation(1, RealtyTypes.TYPE_APARTMENT);
        Assert.assertThat(list, new ListMatcher<Integer>(new ArrayList<Integer>()));

    }

    public List<Requirement> dataSetRequirements1(){
        List<Requirement> requirements = new ArrayList<>();

        Requirement requirement;

        requirement = new Requirement(0, RealtyTypes.TYPE_APARTMENT, 10);
        requirements.add(requirement);

        requirement = new Requirement(1, RealtyTypes.TYPE_APARTMENT, 20);
        requirements.add(requirement);

        requirement = new Requirement(2, RealtyTypes.TYPE_APARTMENT, 30);
        requirements.add(requirement);

        requirement = new Requirement(3, RealtyTypes.TYPE_APARTMENT, 40);
        requirements.add(requirement);

        requirement = new Requirement(4, RealtyTypes.TYPE_APARTMENT, 50);
        requirements.add(requirement);

        requirement = new Requirement(5, RealtyTypes.TYPE_APARTMENT, 60);
        requirements.add(requirement);

        return requirements;
    }

    public List<Recommendation> dataSetRecommendations1() {
        List<Recommendation> recommendations = new ArrayList<>();
        Recommendation recommendation;

        recommendation = new Recommendation(0, 100, RealtyTypes.TYPE_APARTMENT);
        recommendations.add(recommendation);

        recommendation = new Recommendation(1, 200, RealtyTypes.TYPE_APARTMENT);
        recommendations.add(recommendation);

        recommendation = new Recommendation(1, 300, RealtyTypes.TYPE_APARTMENT);
        recommendations.add(recommendation);

        recommendation = new Recommendation(1, 400, RealtyTypes.TYPE_APARTMENT);
        recommendations.add(recommendation);

        recommendation = new Recommendation(4, 500, RealtyTypes.TYPE_APARTMENT);
        recommendations.add(recommendation);

        recommendation = new Recommendation(5, 600, RealtyTypes.TYPE_APARTMENT);
        recommendations.add(recommendation);


        return recommendations;
    }

}
