package dpiki.notificator.network.dataobjects;

/**
 * Created by prog1 on 02.08.2016.
 */
public class HouseholdsReq extends RequirementBase {
    public Long idAddress;
    public Double costFrom;
    public Double costTo;
    public Integer firm;
    public Double totalAreaFrom;
    public Double totalAreaTo;
    public Double livingAreaFrom;
    public Double livingAreaTo;
    public Double kitchenAreaFrom;
    public Double kitchenAreaTo;
    public Integer steadFrom;
    public Integer steadTo;
    public Long idState;
    public Long idWallMaterial;
    public Long idEntry;
    public Long idFurniture;

    public HouseholdsReq() {

    }

    public HouseholdsReq(Long idRequirements) {
        super(idRequirements);
    }

}
