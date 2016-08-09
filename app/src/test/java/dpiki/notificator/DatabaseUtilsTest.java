package dpiki.notificator;

import android.content.Context;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;
import java.util.List;

import dpiki.notificator.data.RealtyTypes;
import dpiki.notificator.data.Recommendation;
import dpiki.notificator.data.Requirement;

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
    public void testRequirementUpdate() {
        List<Requirement> listRequirements1 = dataSetRequirements1();
        List<Requirement> listRequirements2 = dataSetRequirements2();

        databaseUtils.updateRequirements(listRequirements1);
        Assert.assertThat(databaseUtils.readRequirements(), new ListMatcher<>(listRequirements1));

        databaseUtils.updateRequirements(listRequirements2);
        Assert.assertThat(databaseUtils.readRequirements(), new ListMatcher<>(listRequirements2));

        databaseUtils.updateRequirements(new ArrayList<Requirement>());
        Assert.assertThat(databaseUtils.readRequirements(), new ListMatcher<>(new ArrayList<Requirement>()));
    }

    @Test
    public void testRecommendationsAreAddedSuccessfullyAndDissapearingWhenRemove() {
        List<Requirement> requirements1 = dataSetRequirements1();
        databaseUtils.updateRequirements(requirements1);

        databaseUtils.addRecommendations(dataSetRecommendations1());
        Assert.assertThat(databaseUtils.readRecommendation(0L, RealtyTypes.TYPE_APARTMENT),
                new ListMatcher<>(map(dataSetRecommendationsId0TApartment())));
        Assert.assertThat(databaseUtils.readRecommendation(1L, RealtyTypes.TYPE_APARTMENT),
                new ListMatcher<>(map(dataSetRecommendationsId1TApartment())));
        Assert.assertThat(databaseUtils.readRecommendation(2L, RealtyTypes.TYPE_RENT),
                new ListMatcher<>(map(dataSetRecommendationsId2TRent())));

        requirements1.get(0).unreadRecommendations += 3;
        requirements1.get(1).unreadRecommendations += 2;
        requirements1.get(2).unreadRecommendations += 1;
        List<Requirement> ref = new ArrayList<>();
        ref.addAll(requirements1);
        Assert.assertThat(databaseUtils.readRequirements(), new ListMatcher<>(ref));

        databaseUtils.updateRequirements(new ArrayList<Requirement>());
        Assert.assertThat(databaseUtils.readRecommendation(0L, RealtyTypes.TYPE_APARTMENT),
                new ListMatcher<>(new ArrayList<Long>()));
        Assert.assertThat(databaseUtils.readRecommendation(1L, RealtyTypes.TYPE_APARTMENT),
                new ListMatcher<>(new ArrayList<Long>()));
        Assert.assertThat(databaseUtils.readRecommendation(3L, RealtyTypes.TYPE_COMMERCIAL),
                new ListMatcher<>(new ArrayList<Long>()));
        Assert.assertThat(databaseUtils.readRecommendation(4L, RealtyTypes.TYPE_COMMERCIAL),
                new ListMatcher<>(new ArrayList<Long>()));
        Assert.assertThat(databaseUtils.readRecommendation(2L, RealtyTypes.TYPE_RENT),
                new ListMatcher<>(new ArrayList<Long>()));
        Assert.assertThat(databaseUtils.readRecommendation(3L, RealtyTypes.TYPE_LAND),
                new ListMatcher<>(new ArrayList<Long>()));
    }

    public List<Requirement> dataSetRequirements1(){
        List<Requirement> requirements = new ArrayList<>();
        Requirement requirement;
        requirement = new Requirement(0L, RealtyTypes.TYPE_APARTMENT, 10);
        requirements.add(requirement);
        requirement = new Requirement(1L, RealtyTypes.TYPE_APARTMENT, 20);
        requirements.add(requirement);
        requirement = new Requirement(2L, RealtyTypes.TYPE_RENT, 30);
        requirements.add(requirement);
        requirement = new Requirement(3L, RealtyTypes.TYPE_LAND, 40);
        requirements.add(requirement);
        requirement = new Requirement(4L, RealtyTypes.TYPE_COMMERCIAL, 50);
        requirements.add(requirement);
        requirement = new Requirement(3L, RealtyTypes.TYPE_COMMERCIAL, 60);
        requirements.add(requirement);
        return requirements;
    }

    public List<Requirement> dataSetRequirements2(){
        List<Requirement> requirements = new ArrayList<>();
        Requirement requirement;
        requirement = new Requirement(0L, RealtyTypes.TYPE_RENT, 10);
        requirements.add(requirement);
        requirement = new Requirement(1L, RealtyTypes.TYPE_HOUSEHOLD, 20);
        requirements.add(requirement);
        requirement = new Requirement(2L, RealtyTypes.TYPE_HOUSEHOLD, 30);
        requirements.add(requirement);
        requirement = new Requirement(3L, RealtyTypes.TYPE_HOUSEHOLD, 40);
        requirements.add(requirement);
        requirement = new Requirement(4L, RealtyTypes.TYPE_RENT, 50);
        requirements.add(requirement);
        requirement = new Requirement(4L, RealtyTypes.TYPE_APARTMENT, 60);
        requirements.add(requirement);
        return requirements;
    }

    public List<Recommendation> dataSetRecommendations1() {
        List<Recommendation> recommendations = new ArrayList<>();
        recommendations.addAll(dataSetRecommendationsId0TApartment());
        recommendations.addAll(dataSetRecommendationsId1TApartment());
        recommendations.addAll(dataSetRecommendationsId2TRent());
        return recommendations;
    }

    private List<Recommendation> dataSetRecommendationsId0TApartment() {
        List<Recommendation> r = new ArrayList<>();
        r.add(new Recommendation(0L, 100L, RealtyTypes.TYPE_APARTMENT));
        r.add(new Recommendation(0L, 200L, RealtyTypes.TYPE_APARTMENT));
        r.add(new Recommendation(0L, 300L, RealtyTypes.TYPE_APARTMENT));
        return r;
    }

    private List<Recommendation> dataSetRecommendationsId1TApartment() {
        List<Recommendation> r = new ArrayList<>();
        r.add(new Recommendation(1L, 100L, RealtyTypes.TYPE_APARTMENT));
        r.add(new Recommendation(1L, 200L, RealtyTypes.TYPE_APARTMENT));
        return r;
    }

    private List<Recommendation> dataSetRecommendationsId2TRent() {
        List<Recommendation> r = new ArrayList<>();
        r.add(new Recommendation(2L, 100L, RealtyTypes.TYPE_RENT));
        return r;
    }

    private List<Long> map(List<Recommendation> r) {
        List<Long> ret = new ArrayList<>();
        for (Recommendation i : r) {
            ret.add(i.idRealty);
        }
        return ret;
    }
}
