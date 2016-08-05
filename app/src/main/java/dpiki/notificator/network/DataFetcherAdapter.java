package dpiki.notificator.network;

import java.util.List;

import dpiki.notificator.network.dataobjects.RealtyBase;
import dpiki.notificator.network.dataobjects.RequirementBase;

/**
 * Created by Lenovo on 05.08.2016.
 */
public interface DataFetcherAdapter {
    List<RequirementBase> getRequirements(Integer agentId);
    List<RealtyBase> getRealty(String date);
    String getType();
}
