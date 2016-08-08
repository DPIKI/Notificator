package dpiki.notificator.network.dataobjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prog1 on 01.08.2016.
 */
public class Apartment extends RealtyBase {
    public Long idTypeApartment;
    public Double cost;
    public Double totalArea;
    public Double livingArea;
    public Double kitchenArea;
    public Integer floor;
    public Integer floorAll;
    public Long idFund;
    public Long idState;
    public Long idWallMaterial;

    public Apartment() {

    }

    public Apartment(Long id, Long idAddress, Integer firm) {
        super(id, idAddress, firm);
    }

    @Override
    public boolean isMatch(RequirementBase reqBase) {
        if (!(reqBase instanceof ApartmentReq))
            return false;

        ApartmentReq requirement = (ApartmentReq) reqBase;
        return true;
    }

}
