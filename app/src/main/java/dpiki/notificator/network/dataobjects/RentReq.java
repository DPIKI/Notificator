package dpiki.notificator.network.dataobjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prog1 on 02.08.2016.
 */
public class RentReq {

    @SerializedName("")
    public Integer id;

    @SerializedName("")
    public String typeApartment;

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
    public Integer floor;

    @SerializedName("")
    public Integer floorAll;

    @SerializedName("")
    public Integer notFirst;

    @SerializedName("")
    public Integer notLast;

    @SerializedName("")
    public String state;

    @SerializedName("")
    public Integer roomCountFrom;

    @SerializedName("")
    public Integer roomCountTo;

    @SerializedName("")
    public Integer prepayment;

    @SerializedName("")
    public String comfort;

    @SerializedName("")
    public String furniture;

    @SerializedName("")
    public String yard;

    @SerializedName("")
    public String entry;

    @SerializedName("")
    public String phone;

    @SerializedName("")
    public String dateFreedFrom;

    @SerializedName("")
    public String dateFreedTo;

    @SerializedName("")
    public String rent;
}
