package dpiki.notificator.data.requirements;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by prog1 on 02.08.2016.
 */
public class HouseholdsReq {

    @SerializedName("")
    Integer id;

    @SerializedName("")
    String name;

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
    String objectSelect;

    @SerializedName("")
    Integer steadLow;

    @SerializedName("")
    Integer steadHigh;

    @SerializedName("")
    Double totalAreaLow;

    @SerializedName("")
    Double totalAreaHigh;

    @SerializedName("")
    Double livingAreaLow;

    @SerializedName("")
    Double livingAreaHigh;

    @SerializedName("")
    Double kitchenAreaLow;

    @SerializedName("")
    Double kitchenAreaHigh;

    @SerializedName("")
    String typeComfort;

    @SerializedName("")
    String typeWallMaterial;

    @SerializedName("")
    String typeRealestateState;

    @SerializedName("")
    String typeEntry;
}
