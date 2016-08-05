package dpiki.notificator.network.dataobjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lenovo on 01.08.2016.
 */
public class Rent extends RealtyBase {

    public Rent() {

    }

    public Rent(Integer id, String createdAt) {
        super(id, createdAt);
    }

    @Override
    public boolean isMatch(RequirementBase reqBase) {
        if (!(reqBase instanceof RentReq))
            return false;
        RentReq requirement = (RentReq) reqBase;
        return true;
    }

    @SerializedName("")
    public String typeApartment;

    @SerializedName("")
    public Integer floor;

    @SerializedName("")
    public Integer floorAll;

    @SerializedName("")
    public String typeRealestateState;

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
    public Double prepayment;

    @SerializedName("")
    public Integer roomCount;

    @SerializedName("")
    public String materialWindow;

    @SerializedName("")
    public String typeComfort;

    @SerializedName("")
    public String typeFurniture;

    @SerializedName("")
    public String aboutFurniture;

    @SerializedName("")
    public String typeYard;

    @SerializedName("")
    public String typeEntry;

    @SerializedName("")
    public Integer hasPhone;
}
