package dpiki.notificator.network.dataobjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prog1 on 01.08.2016.
 */
public class Apartment extends RealtyBase {

    @Override
    public boolean isMatch(RequirementBase reqBase) {
        if (!(reqBase instanceof ApartmentReq))
            return false;
        ApartmentReq requirement = (ApartmentReq) reqBase;
        return true;
    }

    public Integer idTypeApartment;

    public Double cost;

    public Double totalArea;

    public Double livingArea;

    public Double kitchenArea;

    public Integer floor;

    public Integer floorAll;

    public Integer idFund;

    public Integer idState;

    public Integer idWallMaterial;

}
