package dpiki.notificator.network.dataobjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prog1 on 02.08.2016.
 */
public class RentReq {

    @SerializedName("")
    Integer id;

    @SerializedName("")
    String typeApartment;

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
    Integer floor;

    @SerializedName("")
    Integer floorAll;

    @SerializedName("")
    Integer notFirst;

    @SerializedName("")
    Integer notLast;

    @SerializedName("")
    String state;

    @SerializedName("")
    Integer roomCountFrom;

    @SerializedName("")
    Integer roomCountTo;

    @SerializedName("")
    Integer prepayment;

    @SerializedName("")
    String comfort;

    @SerializedName("")
    String furniture;

    @SerializedName("")
    String yard;

    @SerializedName("")
    String entry;

    @SerializedName("")
    String phone;

    @SerializedName("")
    String dateFreedFrom;

    @SerializedName("")
    String dateFreedTo;

    @SerializedName("")
    String rent;
}
