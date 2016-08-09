package dpiki.notificator.network.dataobjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lenovo on 01.08.2016.
 */
public class Rent extends RealtyBase {
    public Double cost;
    public Integer floor;
    public Integer floorAll;
    public Long idState;
    public Integer roomCount;
    public Integer prepayment;
    public Long idComfort;
    public Long idFurniture;
    public Long idYard;
    public Integer hasPhone;
    public String dateFreed; // TODO : where?
    public Long[] idRent;

    public Rent() {

    }

    public Rent(Long id, Long idAddress, Integer firm) {
        super(id, idAddress, firm);
    }

    @Override
    public boolean isMatch(RequirementBase reqBase) {
        if (!(reqBase instanceof RentReq))
            return false;
        RentReq requirement = (RentReq) reqBase;
        return true;
    }
}
