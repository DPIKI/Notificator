package dpiki.notificator.network.dataobjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prog1 on 02.08.2016.
 */
public class RentReq extends RequirementBase {

    public RentReq() {

    }

    public RentReq(Integer idRequirements) {
        super(idRequirements);
    }

    @SerializedName("id_type_apartment")
    public Integer idTypeApartment;

    @SerializedName("id_address")
    public Integer idAddress;

    @SerializedName("cost_from")
    public Float costFrom;

    @SerializedName("cost_to")
    public Float costTo;

    @SerializedName("firm")
    public Boolean firm;

    @SerializedName("with_photo")
    public Boolean withPhoto;

    @SerializedName("id_variant")
    public Integer idVariant;

    @SerializedName("floor")
    public Integer floor;

    @SerializedName("floor_all")
    public Integer floorAll;

    @SerializedName("not_first")
    public Boolean notFirst;

    @SerializedName("not_last")
    public Boolean notLast;

    @SerializedName("id_state")
    public Integer idState;

    @SerializedName("room_count_from")
    public Integer roomCountFrom;

    @SerializedName("room_count_to")
    public Integer roomCountTo;

    @SerializedName("prepayment")
    public Integer prepayment;

    @SerializedName("id_comfort")
    public Integer idComfort;

    @SerializedName("id_furniture")
    public Integer idFurniture;

    @SerializedName("id_yard")
    public Integer idYard;

    @SerializedName("id_entry")
    public Integer idEntry;

    @SerializedName("id_phone")
    public Integer idPhone;

    @SerializedName("date_freed_from")
    public String dateFreedFrom;

    @SerializedName("date_freed_to")
    public String dateFreedTo;

    @SerializedName("id_rent")
    public Integer idRent;
}
