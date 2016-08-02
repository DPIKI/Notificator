package dpiki.notificator.network.dataobjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prog1 on 02.08.2016.
 */
public class ApartmentReq {
    @SerializedName("")
    public Integer id;

    @SerializedName("")
    public String typeApartment;

    @SerializedName("")
    public Boolean buildingOption;

    @SerializedName("")
    public Boolean finished;

    @SerializedName("")
    public String address;

    @SerializedName("")
    public Float costFrom;

    @SerializedName("")
    public Float costTo;

    @SerializedName("")
    public Boolean firm;

    @SerializedName("")
    public Boolean withPhoto;

    @SerializedName("")
    public String variant;

    @SerializedName("")
    public Double totalAreaFrom;

    @SerializedName("")
    public Double totalAreaTo;

    @SerializedName("")
    public Double livingAreaFrom;

    @SerializedName("")
    public Double livingAreaTo;

    @SerializedName("")
    public Double kitchenAreaFrom;

    @SerializedName("")
    public Double kitchenAreaTo;

    @SerializedName("")
    public Integer floor;

    @SerializedName("")
    public Integer floorAll;

    @SerializedName("")
    public Integer notFirst;

    @SerializedName("")
    public Integer notLast;

    @SerializedName("")
    public String fund;

    @SerializedName("")
    public String state;

    @SerializedName("")
    public String wallMaterial;
}
