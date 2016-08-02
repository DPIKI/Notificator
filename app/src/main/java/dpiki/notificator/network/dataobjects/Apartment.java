package dpiki.notificator.network.dataobjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prog1 on 01.08.2016.
 */
public class Apartment {

    @SerializedName("")
    public Integer id;

    @SerializedName("")
    public String typeApartment;

    @SerializedName("")
    public Integer floor;

    @SerializedName("")
    public Integer floorAll;

    @SerializedName("")
    public String typeWallMaterialApartment;

    @SerializedName("")
    public String state;

    @SerializedName("")
    public Double cost;

    @SerializedName("")
    public String typeFund;

    @SerializedName("")
    public Integer isBuildFinished; // 0 or 1

    @SerializedName("")
    public String typeBuildFinished; // if isBuildFinished == true

    @SerializedName("")
    public String dateBuildFinished;

    @SerializedName("")
    public Integer isLuxury; // 0 or 1

    @SerializedName("")
    public Double totalArea;

    @SerializedName("")
    public Double livingArea;

    @SerializedName("")
    public Double kitchenArea;

    @SerializedName("")
    public Float specialPrice;

    @SerializedName("")
    public Float terms;

    @SerializedName("")
    public String conditions;

    @SerializedName("")
    public Integer isUrgentSale; // 0 or 1

    @SerializedName("")
    public Integer isMortgage; // 0 or 1

    @SerializedName("")
    public String materialFloor;

    @SerializedName("")
    public String materialWindow;

    @SerializedName("")
    public String typeBathroom;

    @SerializedName("")
    public String typeComfort;

    @SerializedName("")
    public String typeHotWater;

    @SerializedName("")
    public String typeHeating;

    @SerializedName("")
    public String typeLoggia;

    @SerializedName("")
    public String typeBalcon;

    @SerializedName("")
    public Integer hasElevator;

    @SerializedName("")
    public String typeCeiling;

    @SerializedName("")
    public String isAdjacent;

    @SerializedName("")
    public String renting;

    @SerializedName("")
    public String construction;

    @SerializedName("")
    public String getTypeLocation;

    @SerializedName("")
    public String srcLayout;
}
