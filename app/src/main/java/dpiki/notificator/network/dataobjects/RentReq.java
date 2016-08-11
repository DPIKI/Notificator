package dpiki.notificator.network.dataobjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prog1 on 02.08.2016.
 */
public class RentReq extends RequirementBase {

    public Long idTypeApartment;
    public Long idAddress;
    public Double costFrom;
    public Double costTo;
    public Integer firm;
    public Integer floor;
    public Integer floorAll;
    public Integer notFirst;
    public Integer notLast;
    public Long idState;
    public Integer roomCountFrom;
    public Integer roomCountTo;
    public Integer prepayment;
    public Long idComfort;
    public Long idFurniture;
    public Long idYard;
    public Long idEntry;
    public Integer idPhone;
    public String dateFreedFrom;
    public String dateFreedTo;
    public Long idRent;

    public RentReq() {

    }

    public RentReq(Long idRequirements) {
        super(idRequirements);
    }

}
