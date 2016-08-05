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
public class DataFetcher<TRealty, TRequirement> {
    private String mClassName;
    public PrefManager mPrefManager;
    public DatabaseUtils mDbUtils;
    public DataFetcherAdapter<TRealty, TRequirement> mAdapter;

    public DataFetcher(String mClassName,
                       PrefManager mPrefManager,
                       DatabaseUtils mDbUtils,
                       DataFetcherAdapter<TRealty, TRequirement> mAdapter) {
        this.mClassName = mClassName;
        this.mPrefManager = mPrefManager;
        this.mDbUtils = mDbUtils;
        this.mAdapter = mAdapter;
    }

    public void fetch(List<Recommendation> r) {
        String strLastDate = mPrefManager.getLastFetchDate(mClassName);
        mPrefManager.putLastFetchDate(mClassName, strLastDate);

        List<TRequirement> tRequirements = mAdapter.getRequirements(0); // TODO: replace with real agentId
        if (tRequirements == null || tRequirements.isEmpty())
            return;

        List<Requirement> requirements = new ArrayList<>();
        for (TRequirement i : tRequirements) {
            requirements.add(mAdapter.mapRequirement(i));
        }
        mDbUtils.updateRequirements(requirements, mAdapter.getType());

        List<TRealty> realty = mAdapter.getRealty(strLastDate);
        if (realty == null || realty.isEmpty())
            return;

        for (TRequirement i : tRequirements) {
            for (TRealty j : realty) {
                if (mAdapter.isMatch(i, j)) {
                    Requirement req = mAdapter.mapRequirement(i);
                    Realty realEstate = mAdapter.mapRealty(j);
                    r.add(new Recommendation(req, realEstate));
                }
            }
        }

        mPrefManager.putLastFetchDate(mClassName, mAdapter.newLastDate(realty));
    }
}
