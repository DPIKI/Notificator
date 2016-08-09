package dpiki.notificator.network.dataobjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lenovo on 01.08.2016.
 */
public class Households extends RealtyBase {
    public Integer stead;
    public Double cost;
    public Double totalArea;
    public Double livingArea;
    public Double kitchenArea;
    public Long idState;
    public Long idWallMaterial;
    public Long idEntry;
    public Long idFurniture;

    public Households() {

    }

    public Households(Long id, Long idAddress, Integer firm) {
        super(id, idAddress, firm);
    }

    @Override
    public boolean isMatch(RequirementBase reqBase) {
        if (!(reqBase instanceof HouseholdsReq))
            return false;
        HouseholdsReq requirement = (HouseholdsReq) reqBase;
        return true;
    }
}
