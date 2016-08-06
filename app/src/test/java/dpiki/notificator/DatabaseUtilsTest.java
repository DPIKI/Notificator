package dpiki.notificator;

import android.content.Context;
import android.content.res.AssetFileDescriptor;

import org.bouncycastle.ocsp.Req;
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
    public void testRequirementUpdate() {
        List<Requirement> listRequirements1 = dataSetRequirements1();
        List<Requirement> listRequirements2 = dataSetRequirements2();

        databaseUtils.updateRequirements(listRequirements1, RealtyTypes.TYPE_APARTMENT);
        Assert.assertThat(databaseUtils.readRequirements(), new ListMatcher<>(listRequirements1));

        databaseUtils.updateRequirements(listRequirements2, RealtyTypes.TYPE_RENT);
        List<Requirement> ref = new ArrayList<>();
        ref.addAll(listRequirements1);
        ref.addAll(listRequirements2);
        Assert.assertThat(databaseUtils.readRequirements(), new ListMatcher<>(ref));

        databaseUtils.updateRequirements(new ArrayList<Requirement>(), RealtyTypes.TYPE_APARTMENT);
        Assert.assertThat(databaseUtils.readRequirements(), new ListMatcher<>(listRequirements2));

        databaseUtils.updateRequirements(new ArrayList<Requirement>(), RealtyTypes.TYPE_RENT);
        Assert.assertThat(databaseUtils.readRequirements(), new ListMatcher<>(new ArrayList<Requirement>()));
    }

    @Test
    public void testRecommendationsAreAddedSuccessfully() {
        List<Requirement> requirements1 = dataSetRequirements1();
        List<Requirement> requirements2 = dataSetRequirements2();
        databaseUtils.updateRequirements(requirements1, RealtyTypes.TYPE_APARTMENT);
        databaseUtils.updateRequirements(requirements2, RealtyTypes.TYPE_RENT);

        databaseUtils.addRecommendations(dataSetRecommendations1());
        Assert.assertThat(databaseUtils.readRecommendation(0, RealtyTypes.TYPE_APARTMENT),
                new ListMatcher<>(map(dataSetRecommendationsId0TApartment())));
        Assert.assertThat(databaseUtils.readRecommendation(1, RealtyTypes.TYPE_APARTMENT),
                new ListMatcher<>(map(dataSetRecommendationsId1TApartment())));
        Assert.assertThat(databaseUtils.readRecommendation(2, RealtyTypes.TYPE_RENT),
                new ListMatcher<>(map(dataSetRecommendationsId2TApartment())));

        requirements1.get(0).unreadRecommendations += 3;
        requirements1.get(1).unreadRecommendations += 2;
        requirements2.get(2).unreadRecommendations += 1;
        List<Requirement> ref = new ArrayList<>();
        ref.addAll(requirements1);
        ref.addAll(requirements2);
        Assert.assertThat(databaseUtils.readRequirements(), new ListMatcher<>(ref));
        databaseUtils.updateRequirements(new ArrayList<Requirement>(), RealtyTypes.TYPE_APARTMENT);
        databaseUtils.updateRequirements(new ArrayList<Requirement>(), RealtyTypes.TYPE_RENT);
    }

    @Test
    public void testRecommendationsToDisappearedRequirementsRemoved(){
        List<Requirement> listRequirements1 = dataSetRequirements1();
        List<Requirement> listRequirements2 = dataSetRequirements2();
        List<Recommendation> listRecommendation = dataSetRecommendations1();
        databaseUtils.updateRequirements(listRequirements1, RealtyTypes.TYPE_APARTMENT);
        databaseUtils.updateRequirements(listRequirements2, RealtyTypes.TYPE_APARTMENT);
        databaseUtils.addRecommendations(listRecommendation);
        listRequirements1.remove(0);
        listRequirements1.remove(0);
        databaseUtils.updateRequirements(listRequirements1, RealtyTypes.TYPE_APARTMENT);

        List<Integer> list = databaseUtils.readRecommendation(0, RealtyTypes.TYPE_APARTMENT);
        Assert.assertThat(list, new ListMatcher<>(new ArrayList<Integer>()));

        list = databaseUtils.readRecommendation(1, RealtyTypes.TYPE_APARTMENT);
        Assert.assertThat(list, new ListMatcher<>(new ArrayList<Integer>()));

        list = databaseUtils.readRecommendation(2, RealtyTypes.TYPE_RENT);
        Assert.assertThat(list, new ListMatcher<>(map(dataSetRecommendationsId2TApartment())));

        databaseUtils.updateRequirements(new ArrayList<Requirement>(), RealtyTypes.TYPE_APARTMENT);
        databaseUtils.updateRequirements(new ArrayList<Requirement>(), RealtyTypes.TYPE_RENT);

        list = databaseUtils.readRecommendation(0, RealtyTypes.TYPE_APARTMENT);
        Assert.assertThat(list, new ListMatcher<>(new ArrayList<Integer>()));

        list = databaseUtils.readRecommendation(1, RealtyTypes.TYPE_APARTMENT);
        Assert.assertThat(list, new ListMatcher<>(new ArrayList<Integer>()));

        list = databaseUtils.readRecommendation(2, RealtyTypes.TYPE_RENT);
        Assert.assertThat(list, new ListMatcher<>(new ArrayList<Integer>()));
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

    public List<Requirement> dataSetRequirements2(){
        List<Requirement> requirements = new ArrayList<>();
        Requirement requirement;
        requirement = new Requirement(0, RealtyTypes.TYPE_RENT, 10);
        requirements.add(requirement);
        requirement = new Requirement(1, RealtyTypes.TYPE_RENT, 20);
        requirements.add(requirement);
        requirement = new Requirement(2, RealtyTypes.TYPE_RENT, 30);
        requirements.add(requirement);
        requirement = new Requirement(3, RealtyTypes.TYPE_RENT, 40);
        requirements.add(requirement);
        requirement = new Requirement(4, RealtyTypes.TYPE_RENT, 50);
        requirements.add(requirement);
        requirement = new Requirement(5, RealtyTypes.TYPE_RENT, 60);
        requirements.add(requirement);
        return requirements;
    }

    public List<Recommendation> dataSetRecommendations1() {
        List<Recommendation> recommendations = new ArrayList<>();
        recommendations.addAll(dataSetRecommendationsId0TApartment());
        recommendations.addAll(dataSetRecommendationsId1TApartment());
        recommendations.addAll(dataSetRecommendationsId2TApartment());
        return recommendations;
    }

    private List<Recommendation> dataSetRecommendationsId0TApartment() {
        List<Recommendation> r = new ArrayList<>();
        r.add(new Recommendation(0, 100, RealtyTypes.TYPE_APARTMENT));
        r.add(new Recommendation(0, 200, RealtyTypes.TYPE_APARTMENT));
        r.add(new Recommendation(0, 300, RealtyTypes.TYPE_APARTMENT));
        return r;
    }

    private List<Recommendation> dataSetRecommendationsId1TApartment() {
        List<Recommendation> r = new ArrayList<>();
        r.add(new Recommendation(1, 100, RealtyTypes.TYPE_APARTMENT));
        r.add(new Recommendation(1, 200, RealtyTypes.TYPE_APARTMENT));
        return r;
    }

    private List<Recommendation> dataSetRecommendationsId2TApartment() {
        List<Recommendation> r = new ArrayList<>();
        r.add(new Recommendation(2, 100, RealtyTypes.TYPE_RENT));
        return r;
    }

    private List<Integer> map(List<Recommendation> r) {
        List<Integer> ret = new ArrayList<>();
        for (Recommendation i : r) {
            ret.add(i.idRealty);
        }
        return ret;
    }
}
