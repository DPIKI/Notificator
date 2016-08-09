package dpiki.notificator.network.dataobjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prog1 on 02.08.2016.
 */
public class HouseholdsReq extends RequirementBase {
    public Long idAddress;
    public Float costFrom;
    public Float costTo;
    public Boolean firm;
    public Float totalAreaFrom;
    public Float totalAreaTo;
    public Float livingAreaFrom;
    public Float livingAreaTo;
    public Float kitchenAreaFrom;
    public Float kitchenAreaTo;
    public Float steadFrom;
    public Float steadTo;
    public Long idState;
    public Long idWallMaterial;
    public Long idEntry;
    public Long idFurniture;

    public HouseholdsReq() {

    }

    public HouseholdsReq(Integer idRequirements) {
        super(idRequirements);
    }

}
