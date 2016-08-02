package dpiki.notificator.data.requirements;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by prog1 on 02.08.2016.
 */
public class RentReq {

    @SerializedName("")
    Integer id;

    @SerializedName("")
    String name;

    @SerializedName("")
    List<String> typeApartment;

    @SerializedName("")
    String city;

    @SerializedName("")
    List<String> district;

    @SerializedName("")
    Double costLow;

    @SerializedName("")
    Double costHigh;

    @SerializedName("")
    Integer firm; //???

    @SerializedName("")
    Integer photoOnly;

    @SerializedName("")
    String objectSelect; //???

    @SerializedName("")
    Integer floor;

    @SerializedName("")
    Integer floorAll;

    @SerializedName("")
    Integer notFirst;

    @SerializedName("")
    Integer notLast;

    @SerializedName("")
    String typeRealestateState;

    @SerializedName("")
    String typeFurniture;

    @SerializedName("")
    String typeComfort;

    @SerializedName("")
    String typeYard;

    @SerializedName("")
    String typeEntry;

    @SerializedName("")
    String typeRent;

    @SerializedName("")
    Double prepayment;

    @SerializedName("")
    Integer hasPhone;

    @SerializedName("")
    Integer roomCountLow;

    @SerializedName("")
    Integer roomCountHigh;

    @SerializedName("")
    String dateLow;

    @SerializedName("")
    String dateHigh;
}
