package dpiki.notificator.network.dataobjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prog1 on 02.08.2016.
 */
public class CommercialReq extends RequirementBase {
    public Long idAddress;
    public Integer firm;
    public Float totalAreaFrom;
    public Float totalAreaTo;
    public Float hallAreaFrom;
    public Float hallAreaTo;
    public Float landAreaFrom;
    public Float landAreaTo;
    public Float rentAreaFrom;
    public Float rentAreaTo;
    public Float sellPriceFrom;
    public Float sellPriceTo;
    public Float sellPriceSquareMeterFrom;
    public Float sellPriceSquareMeterTo;
    public Float rentalPriceFrom;
    public Float rentalPriceTo;
    public Float rentalPriceSquareMeterFrom;
    public Float rentalPriceSquareMeterTo;
    public Long idLiftingEquipment;
    public Long idCommunication;

    public CommercialReq() {

    }

    public CommercialReq(Long idRequirements) {
        super(idRequirements);
    }

}
