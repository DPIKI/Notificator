package dpiki.notificator.network;

import java.util.List;

import dpiki.notificator.data.Realty;
import dpiki.notificator.data.Requirement;

/**
 * Created by Lenovo on 05.08.2016.
 */
public interface DataFetcherAdapter<TRealty, TRequirement> {
    List<TRequirement> getRequirements(Integer agentId);
    List<TRealty> getRealty(String date);
    String getType();
}
