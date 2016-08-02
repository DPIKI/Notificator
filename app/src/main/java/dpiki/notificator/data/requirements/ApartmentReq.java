package dpiki.notificator.data.requirements;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by prog1 on 02.08.2016.
 */
public class ApartmentReq {
    @SerializedName("")
    Integer id;

    @SerializedName("")
    String name;

    @SerializedName("")
    List<String> type;

    @SerializedName("")
    Integer isFinishBuild;

    @SerializedName("")
    String typeFinishBuild;

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
    Integer floor;

    @SerializedName("")
    Integer floorAll;

    @SerializedName("")
    Integer notFirst;

    @SerializedName("")
    Integer notLast;

    @SerializedName("")
    String typeMaterial;

    @SerializedName("")
    String typeFund;

    @SerializedName("")
    String typeRealestateState;
}
