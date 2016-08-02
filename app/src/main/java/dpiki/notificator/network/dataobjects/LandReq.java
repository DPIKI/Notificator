package dpiki.notificator.network.dataobjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prog1 on 02.08.2016.
 */
public class LandReq {

    @SerializedName("")
    Integer id;

    @SerializedName("")
    String address;

    @SerializedName("")
    Float costFrom;

    @SerializedName("")
    Float costTo;

    @SerializedName("")
    Boolean firm;

    @SerializedName("")
    Boolean withPhoto;

    @SerializedName("")
    String variant;

    @SerializedName("")
    Float totalAreaFrom;

    @SerializedName("")
    Float totalAreaTo;

    @SerializedName("")
    Float livingAreaFrom;

    @SerializedName("")
    Float livingAreaTo;

    @SerializedName("")
    Float kitchenAreaFrom;

    @SerializedName("")
    Float kitchenAreaTo;

    @SerializedName("")
    Float steadFrom;

    @SerializedName("")
    Float steadTo;

    @SerializedName("")
    String state;

    @SerializedName("")
    String wallMaterial;

    @SerializedName("")
    String entry;

    @SerializedName("")
    String furniture;
}
