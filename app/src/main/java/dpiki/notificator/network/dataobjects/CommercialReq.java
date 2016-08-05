package dpiki.notificator.network.dataobjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prog1 on 02.08.2016.
 */
public class CommercialReq extends RequirementBase {

    public CommercialReq() {

    }

    public CommercialReq(Integer idRequirements) {
        super(idRequirements);
    }

    @SerializedName("id_address")
    public Integer idAddress;

    @SerializedName("firm")
    public Boolean firm;

    @SerializedName("with_photo")
    public Boolean withPhoto;

    @SerializedName("id_variant")
    public Integer idVariant;

    @SerializedName("total_area_from")
    public Float totalAreaFrom;

    @SerializedName("total_area_to")
    public Float totalAreaTo;

    @SerializedName("hall_area_from")
    public Float hallAreaFrom;

    @SerializedName("hall_area_to")
    public Float hallAreaTo;

    @SerializedName("land_area_from")
    public Float landAreaFrom;

    @SerializedName("land_area_to")
    public Float landAreaTo;

    @SerializedName("rent_area_from")
    public Float rentAreaFrom;

    @SerializedName("rent_area_to")
    public Float rentAreaTo;

    @SerializedName("sell_price_from")
    public Float sellPriceFrom;

    @SerializedName("sell_price_to")
    public Float sellPriceTo;

    @SerializedName("sell_price_square_meter_from")
    public Float sellPriceSquareMeterFrom;

    @SerializedName("sell_price_square_meter_to")
    public Float sellPriceSquareMeterTo;

    @SerializedName("rental_price_from")
    public Float rentalPriceFrom;

    @SerializedName("rental_price_to")
    public Float rentalPriceTo;

    @SerializedName("rental_price_square_meter_from")
    public Float rentalPriceSquareMeterFrom;

    @SerializedName("rental_price_square_meter_to")
    public Float rentalPriceSquareMeterTo;

    @SerializedName("id_lifting_equipment")
    public Integer idLiftingEquipment;

    @SerializedName("id_communication")
    public Integer idCommunication;
}
