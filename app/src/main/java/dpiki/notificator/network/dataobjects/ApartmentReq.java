package dpiki.notificator.network.dataobjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prog1 on 02.08.2016.
 */
public class ApartmentReq {
    @SerializedName("")
    Integer id;

    @SerializedName("")
    String typeApartment;

    @SerializedName("")
    Boolean buildingOption;

    @SerializedName("")
    Boolean finished;

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
    Double totalAreaFrom;

    @SerializedName("")
    Double totalAreaTo;

    @SerializedName("")
    Double livingAreaFrom;

    @SerializedName("")
    Double livingAreaTo;

    @SerializedName("")
    Double kitchenAreaFrom;

    @SerializedName("")
    Double kitchenAreaTo;

    @SerializedName("")
    Integer floor;

    @SerializedName("")
    Integer floorAll;

    @SerializedName("")
    Integer notFirst;

    @SerializedName("")
    Integer notLast;

    @SerializedName("")
    String fund;

    @SerializedName("")
    String state;

    @SerializedName("")
    String wallMaterial;
}
