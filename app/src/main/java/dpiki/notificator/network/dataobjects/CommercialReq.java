package dpiki.notificator.network.dataobjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prog1 on 02.08.2016.
 */
public class CommercialReq extends RequirementBase {

    public Long idAddress;
    public Integer firm;
    public Double totalAreaFrom;
    public Double totalAreaTo;
    public Double hallAreaFrom;
    public Double hallAreaTo;
    public Double landAreaFrom;
    public Double landAreaTo;
    public Double rentAreaFrom;
    public Double rentAreaTo;
    public Double sellPriceFrom;
    public Double sellPriceTo;
    public Double sellPriceSquareMeterFrom;
    public Double sellPriceSquareMeterTo;
    public Double rentalPriceFrom;
    public Double rentalPriceTo;
    public Double rentalPriceSquareMeterFrom;
    public Double rentalPriceSquareMeterTo;
    public Long idLiftingEquipment;
    public Long idCommunication;

    public CommercialReq() {

    }

    public CommercialReq(Long idRequirements) {
        super(idRequirements);
    }

}
