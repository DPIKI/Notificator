package dpiki.notificator.network.dataobjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prog1 on 02.08.2016.
 */
public class LandReq {

    @SerializedName("id_requirements")
    public Integer idRequirements;

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

    @SerializedName("stead_from")
    public Float steadFrom;

    @SerializedName("stead_to")
    public Float steadTo;

    @SerializedName("id_state")
    public Integer idState;

    @SerializedName("id_wall_material")
    public Integer idWallMaterial;

    @SerializedName("idEntry")
    public Integer idEntry;

    @SerializedName("id_furniture")
    public Integer idFurniture;
}
