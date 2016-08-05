package dpiki.notificator.network.dataobjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prog1 on 05.08.2016.
 */
public class RequirementBase {

    @SerializedName("id_requirements")
    public Integer idRequirements;

    public RequirementBase(){

    }

    public RequirementBase(Integer idRequirements) {
        this.idRequirements = idRequirements;
    }
}
