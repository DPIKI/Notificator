package dpiki.notificator.network.dataobjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lenovo on 01.08.2016.
 */
public class Commercial extends RealtyBase {

    public Commercial() {

    }

    public Commercial(Integer id, String createdAt) {
        super(id, createdAt);
    }

    @SerializedName("")
    public String materialWall;

    @SerializedName("")
    public String typeRealestateState;

    @SerializedName("")
    public String dateFinishBuild;

    @SerializedName("")
    public Double totalArea;

    @SerializedName("")
    public Double hallArea;

    @SerializedName("")
    public Double landArea;

    @SerializedName("")
    public Double rentArea;

    @SerializedName("")
    public Integer isUrgentSale;

    @SerializedName("")
    public Integer isMorgage;

    @SerializedName("")
    public Float sellPrice;

    @SerializedName("")
    public Float sellPriceSquareMeter;

    @SerializedName("")
    public Float rentalPrice;

    @SerializedName("")
    public Float rentalPriceSquareMeter;

    @SerializedName("")
    public String materialWindow;

    @SerializedName("")
    public String typeOwnership;

    @SerializedName("")
    public String typeHeating;

    @SerializedName("")
    public Integer hasElevator;

    @SerializedName("")
    public Float elevatorTonnage;

    @SerializedName("")
    public String ceiling;

    @SerializedName("")
    public String typeSecurity;

    @SerializedName("")
    public String typeNumberStoreys;

    @SerializedName("")
    public Integer isStation;

    @SerializedName("")
    public String typeStation;

    @SerializedName("")
    public Integer isLeasedLine;

    @SerializedName("")
    public Integer countNumbers;

    @SerializedName("")
    public Integer countFreePairs;
}
