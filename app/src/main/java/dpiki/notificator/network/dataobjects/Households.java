package dpiki.notificator.network.dataobjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lenovo on 01.08.2016.
 */
public class Households extends RealtyBase {

    public Households() {

    }

    public Households(Integer id, String createdAt) {
        super(id, createdAt);
    }

    @Override
    public boolean isMatch(RequirementBase reqBase) {
        if (!(reqBase instanceof HouseholdsReq))
            return false;
        HouseholdsReq requirement = (HouseholdsReq) reqBase;
        return true;
    }

    @SerializedName("")
    public String materialWall;

    @SerializedName("")
    public String typeRealestateState;

    @SerializedName("")
    public String datePuttingHouse;

    @SerializedName("")
    public Integer floorAll;

    @SerializedName("")
    public Integer stead;

    @SerializedName("")
    public Integer isLuxury;

    @SerializedName("")
    public Double totalArea;

    @SerializedName("")
    public Double livingArea;

    @SerializedName("")
    public Double kitchenArea;

    @SerializedName("")
    public Double cost;

    @SerializedName("")
    public Double specialPrice;

    @SerializedName("")
    public String conditions;

    @SerializedName("")
    public Integer roomCount;

    @SerializedName("")
    public String typeRoof;

    @SerializedName("")
    public String materialWindow;

    @SerializedName("")
    public String typeBathroom;

    @SerializedName("")
    public String typeHotWater;

    @SerializedName("")
    public String typeHeating;

    @SerializedName("")
    public String typeCeiling;

    @SerializedName("")
    public String construction;

    @SerializedName("")
    public String typeYard;

    @SerializedName("")
    public String typeEntry;

    @SerializedName("")
    public Integer hasGarage;

    @SerializedName("")
    public String typeSewerage;

    @SerializedName("")
    public String srcLayout;
}
