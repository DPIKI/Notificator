package dpiki.notificator.network.dataobjects;

import com.google.gson.LongSerializationPolicy;
import com.google.gson.annotations.SerializedName;

/**
 * Created by prog1 on 02.08.2016.
 */
public class ApartmentReq extends RequirementBase {

    public Long idTypeApartment;
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
    public Integer floor;
    public Integer floorAll;
    public Integer notFirst;
    public Integer notLast;
    public Long idFund;
    public Long idState;
    public Long idWallMaterial;

    public ApartmentReq() {

    }

    public ApartmentReq(Long idRequirements) {
        super(idRequirements);
    }

}
