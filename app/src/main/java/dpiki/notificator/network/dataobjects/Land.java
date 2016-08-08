package dpiki.notificator.network.dataobjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lenovo on 01.08.2016.
 */
public class Land extends RealtyBase {
    public Integer stead;
    public Double cost;
    public Double totalArea;
    public Double livingArea;
    public Double kitchenArea;
    public Long idState;
    public Long idWallMaterial;
    public Long idEntry;
    public Long idFurniture;

    public Land() {

    }

    public Land(Long id, Long idAddress, Boolean firm, Boolean withPhoto) {
        super(id, idAddress, firm, withPhoto);
    }

    @Override
    public boolean isMatch(RequirementBase reqBase) {
       if (!(reqBase instanceof LandReq))
          return false;
       LandReq requirement = (LandReq) reqBase;
       return true;
    }
}
