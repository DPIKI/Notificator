package dpiki.notificator.network.dataobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import dpiki.notificator.ui.MainActivity;

/**
 * Created by Lenovo on 01.08.2016.
 */
public class Commercial extends RealtyBase {
    public Double totalArea;
    public Double hallArea;
    public Double landArea;
    public Double rentArea;
    public Double sellPrice;
    public Double sellPriceSquareMeter;
    public Double rentPrice;
    public Double rentPriceSquareMeter;
    public Long[] idLiftingEquipments;
    public Long[] idCommunications;

    public Commercial() {

    }

    public Commercial(Long id, Long idAddress, Boolean firm) {
        super(id, idAddress, firm);
    }

    @Override
    public boolean isMatch(RequirementBase reqBase) {
        if (!(reqBase instanceof CommercialReq))
            return false;
        CommercialReq req = (CommercialReq) reqBase;

        if (req.idAddress != null && this.idAddress != null
                && !req.idAddress.equals(this.idAddress))
            return false;
        if (req.firm != null && this.firm != null
                && !req.firm.equals(this.firm))
            return false;

        if (req.totalAreaFrom != null && this.totalArea != null
                && req.totalAreaFrom > this.totalArea)
            return false;
        if (req.totalAreaTo != null && this.totalArea != null
                && req.totalAreaTo < this.totalArea)
            return false;
        if (req.hallAreaFrom != null && this.hallArea != null
                && req.hallAreaFrom > this.hallArea)
            return false;
        if (req.hallAreaTo != null && this.hallArea != null
                && req.hallAreaTo < this.hallArea)
            return false;
        if (req.landAreaFrom != null && this.landArea != null
                && req.landAreaFrom > this.landArea)
            return false;
        if (req.landAreaTo != null && this.landArea != null
                && req.landAreaTo < this.landArea)
            return false;
        if (req.rentAreaFrom != null && this.rentArea != null
                && req.rentAreaFrom > this.rentArea)
            return false;
        if (req.rentAreaTo != null && this.rentArea != null
                && req.rentAreaTo < this.rentArea)
            return false;

        if (req.sellPriceFrom != null && this.sellPrice != null
                && req.sellPriceFrom > this.sellPrice)
            return false;
        if (req.sellPriceTo != null && this.sellPrice != null
                && req.sellPriceTo < this.sellPrice)
            return false;
        if (req.sellPriceSquareMeterFrom != null && this.sellPriceSquareMeter != null
                && req.sellPriceSquareMeterFrom > this.sellPriceSquareMeter)
            return false;
        if (req.sellPriceSquareMeterTo != null && this.sellPriceSquareMeter != null
                && req.sellPriceSquareMeterTo < this.sellPriceSquareMeter)
            return false;

         if (req.rentalPriceFrom != null && this.rentPrice != null
                && req.rentalPriceFrom > this.rentPrice)
            return false;
        if (req.rentalPriceTo != null && this.rentPrice != null
                && req.rentalPriceTo < this.rentPrice)
            return false;

        if (req.rentalPriceSquareMeterFrom != null && this.rentPriceSquareMeter != null
                && req.rentalPriceSquareMeterFrom > this.rentPriceSquareMeter)
            return false;
        if (req.rentalPriceSquareMeterTo != null && this.rentPriceSquareMeter != null
                && req.rentalPriceSquareMeterTo < this.rentPriceSquareMeter)
            return false;

        if (req.idLiftingEquipment != null && this.idLiftingEquipments != null
                && !Arrays.asList(this.idLiftingEquipments).contains(req.idLiftingEquipment))
            return false;
        if (req.idCommunication != null && this.idCommunications != null
                && !Arrays.asList(this.idCommunications).contains(req.idCommunication))
            return false;

        return true;
    }

}
