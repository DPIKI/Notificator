package dpiki.notificator.network.dataobjects;

/**
 * Created by prog1 on 10.08.2016.
 */
public class RequirementContainer {
    private Long id;
    private String requirementInstanceType;

    public RequirementContainer() {

    }

    public RequirementContainer(Long id, String requirementInstanceType) {
        this.id = id;
        this.requirementInstanceType = requirementInstanceType;
    }

    public void setRequirementInstanceType(String requirementInstanceType) {
        this.requirementInstanceType = requirementInstanceType;
    }

    public String getRequirementInstanceType() {
        return requirementInstanceType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
