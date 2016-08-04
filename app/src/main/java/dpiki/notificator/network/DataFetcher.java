package dpiki.notificator.network;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dpiki.notificator.App;
import dpiki.notificator.DatabaseUtils;
import dpiki.notificator.PrefManager;
import dpiki.notificator.data.Realty;
import dpiki.notificator.data.Recommendation;
import dpiki.notificator.data.Requirement;

/**
 * Created by Lenovo on 07.07.2016.
 */
public abstract class DataFetcher<TRealty, TRequirement> {
    private String mClassName;
    public PrefManager mPrefManager;
    public ServerApiWrapper mApi;
    public DatabaseUtils mDbUtils;

    public DataFetcher(String mClassName, PrefManager mPrefManager, ServerApiWrapper mApi, DatabaseUtils mDbUtils) {
        this.mClassName = mClassName;
        this.mPrefManager = mPrefManager;
        this.mApi = mApi;
        this.mDbUtils = mDbUtils;
    }

    public void fetch(List<Recommendation> r) {
        String strLastDate = mPrefManager.getLastFetchDate(mClassName);
        mPrefManager.putLastFetchDate(mClassName, strLastDate);

        List<TRequirement> tRequirements = getRequirements(0); // TODO: replace with real agentId
        if (tRequirements == null || tRequirements.isEmpty())
            return;

        List<Requirement> requirements = new ArrayList<>();
        for (TRequirement i : tRequirements) {
            requirements.add(mapRequirement(i));
        }
        mDbUtils.updateRequirements(requirements, getType());

        List<TRealty> realty = getRealty(strLastDate);
        if (realty == null || realty.isEmpty())
            return;

        for (TRequirement i : tRequirements) {
            for (TRealty j : realty) {
                if (isMatch(i, j)) {
                    Requirement req = mapRequirement(i);
                    Realty realEstate = mapRealty(j);
                    r.add(new Recommendation(req, realEstate));
                }
            }
        }

        mPrefManager.putLastFetchDate(mClassName, newLastDate(realty));
    }

    protected abstract List<TRequirement> getRequirements(Integer agentId);
    protected abstract List<TRealty> getRealty(String date);
    protected abstract Requirement mapRequirement(TRequirement requirement);
    protected abstract Realty mapRealty(TRealty realty);
    protected abstract String getType();
    protected abstract boolean isMatch(TRequirement requirement, TRealty realty);
    protected abstract String newLastDate(List<TRealty> realty);
}
