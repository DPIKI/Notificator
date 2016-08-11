package dpiki.notificator.network.dataobjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prog1 on 05.08.2016.
 */
public class RequirementBase {
    public Long idRequirements;

    public RequirementBase(){

    }

    public RequirementBase(Long idRequirements) {
        this.idRequirements = idRequirements;
    }

}
