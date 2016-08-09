package dpiki.notificator.network.dataobjects;

import com.google.gson.LongSerializationPolicy;
import com.google.gson.annotations.SerializedName;

/**
 * Created by prog1 on 02.08.2016.
 */
public class ApartmentReq extends RequirementBase {
    public Long idTypeApartment;
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
    public Integer floor;
    public Integer floorAll;
    public Boolean notFirst;
    public Boolean notLast;
    public Integer idFund;
    public Integer idState;
    public Integer idWallMaterial;

    public ApartmentReq() {

    }

    public ApartmentReq(Long idRequirements) {
        super(idRequirements);
    }

}
