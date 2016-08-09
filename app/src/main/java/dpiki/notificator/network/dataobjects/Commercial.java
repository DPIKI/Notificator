package dpiki.notificator.network.dataobjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lenovo on 01.08.2016.
 */
public class Commercial extends RealtyBase {
    public Double totalArea;
    public Double hallArea;
    public Double landArea;
    public Double rentArea;
    public Double sellPrice;
    public Double sellPriceSquereMeter;
    public Double rentPrice;
    public Double rentPriceSquareMeter;
    public Long[] idLiftingEquipments;
    public Long[] idCommunications;

    public Commercial() {

    }

    public Commercial(Long id, Long idAddress, Integer firm) {
        super(id, idAddress, firm);
    }

    @Override
    public boolean isMatch(RequirementBase reqBase) {
        if (!(reqBase instanceof CommercialReq))
            return false;
        CommercialReq requirement = (CommercialReq) reqBase;
        return true;
    }
}
