package dpiki.notificator.data.parameters;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prog1 on 01.08.2016.
 */
public class Apartment {

    @SerializedName("")
    Integer id;

    @SerializedName("")
    String type;

    @SerializedName("")
    Integer floor;

    @SerializedName("")
    Integer floorAll;

    @SerializedName("")
    String materialWall;

    @SerializedName("")
    String state;

    @SerializedName("")
    Double cost;

    @SerializedName("")
    String typeFund;

    @SerializedName("")
    Integer isBuildFinished; // 0 or 1

    @SerializedName("")
    String typeBuildFinished; // if isBuildFinished == true

    @SerializedName("")
    String dateBuildFinished;

    @SerializedName("")
    Integer isLuxury; // 0 or 1

    @SerializedName("")
    Double totalArea;

    @SerializedName("")
    Double livingArea;

    @SerializedName("")
    Double kitchenArea;

    @SerializedName("")
    Float specialPrice;

    @SerializedName("")
    Float terms;

    @SerializedName("")
    String conditions;

    @SerializedName("")
    Integer isUrgentSale; // 0 or 1

    @SerializedName("")
    Integer isMortgage; // 0 or 1

    @SerializedName("")
    String materialFloor;

    @SerializedName("")
    String materialWindow;

    @SerializedName("")
    String typeBathroom;

    @SerializedName("")
    String typeComfort;

    @SerializedName("")
    String typeHotWater;

    @SerializedName("")
    String typeHeating;

    @SerializedName("")
    String typeLoggia;

    @SerializedName("")
    String typeBalcon;

    @SerializedName("")
    Integer hasElevator;

    @SerializedName("")
    String typeCeiling;

    @SerializedName("")
    String isAdjacent;

    @SerializedName("")
    String renting;

    @SerializedName("")
    String construction;

    @SerializedName("")
    String getTypeLocation;

    @SerializedName("")
    String srcLayout;
}
