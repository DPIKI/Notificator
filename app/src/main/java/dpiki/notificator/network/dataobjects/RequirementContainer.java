package dpiki.notificator.network.dataobjects;

import dpiki.notificator.data.RealtyTypes;

/**
 * Created by prog1 on 10.08.2016.
 */
public class RequirementContainer {
    private Long id;
    private String requirementInstanceType;

    public RequirementContainer() {

    }

    public void setRequirementInstanceType(String requirementInstanceType) {
        this.requirementInstanceType = requirementInstanceType;
    }

    public String getRequirementInstanceType() {
        return requirementInstanceType;
    }

    public Long getId() {
        return id;
    }

}
