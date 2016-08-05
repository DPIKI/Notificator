package dpiki.notificator.network.dataobjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prog1 on 02.08.2016.
 */
public class ApartmentReq extends RequirementBase {

    public ApartmentReq() {

    }

    public ApartmentReq(Integer idRequirements) {
        super(idRequirements);
    }

    @SerializedName("id_type_apartment")
    public Integer idTypeApartment;

    @SerializedName("building_option")
    public Boolean buildingOption;

    @SerializedName("finished")
    public Boolean finished;

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

    @SerializedName("total_area_from")
    public Float totalAreaFrom;

    @SerializedName("total_area_to")
    public Float totalAreaTo;

    @SerializedName("living_area_from")
    public Float livingAreaFrom;

    @SerializedName("living_area_to")
    public Float livingAreaTo;

    @SerializedName("kitchen_area_from")
    public Float kitchenAreaFrom;

    @SerializedName("kitchen_area_to")
    public Float kitchenAreaTo;

    @SerializedName("floor")
    public Integer floor;

    @SerializedName("floor_all")
    public Integer floorAll;

    @SerializedName("not_first")
    public Boolean notFirst;

    @SerializedName("not_last")
    public Boolean notLast;

    @SerializedName("id_fund")
    public Integer idFund;

    @SerializedName("id_state")
    public Integer idState;

    @SerializedName("id_wall_material")
    public Integer idWallMaterial;
}
