package dpiki.notificator.network;

import android.content.Context;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import dpiki.notificator.App;
import dpiki.notificator.PrefManager;
import dpiki.notificator.data.Recommendation;

/**
 * Created by Lenovo on 07.07.2016.
 */
public abstract class DataFetcher<Realty, Requirement> {
    private String mClassName;

    @Inject
    public PrefManager mPrefManager;

    @Inject
    public ServerApi api;

    public DataFetcher(String className) {
        this.mClassName = className;
        App.getInstance().inject(this);
    }

    public void fetch(List<Recommendation> r) {
        String strLastDate = mPrefManager.getLastFetchDate(mClassName);
        mPrefManager.putLastFetchDate(mClassName, strLastDate);

        List<Requirement> requirements = getRequirements(0); // TODO: replace with real agentId
        if (requirements == null || requirements.isEmpty())
            return;

        List<Realty> realty = getRealty(strLastDate);
        if (realty == null || realty.isEmpty())
            return;

        for (Requirement i : requirements) {
            for (Realty j : realty) {
                if (isMatch(i, j)) {
                    r.add(makeRecommendation(i, j));
                }
            }
        }

        mPrefManager.putLastFetchDate(mClassName, newLastDate(realty));
    }

    protected abstract List<Requirement> getRequirements(Integer agentId);
    protected abstract List<Realty> getRealty(String date);
    protected abstract Recommendation makeRecommendation(Requirement requirement, Realty realty);
    protected abstract boolean isMatch(Requirement requirement, Realty realty);
    protected abstract String newLastDate(List<Realty> realty);
}
