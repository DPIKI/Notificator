package dpiki.notificator.network.dataobjects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lenovo on 01.08.2016.
 */
public class Land extends RealtyBase {

    public Land() {

    }

    public Land(Integer id, String createdAt) {
        super(id, createdAt);
    }

    @SerializedName("")
    public Integer stead;

    @SerializedName("")
    public Integer steadX;

    @SerializedName("")
    public Integer steadY;

    @SerializedName("")
    public Integer isLuxury;

    @SerializedName("")
    public Integer isGarden;

    @SerializedName("")
    public Double cost;

    @SerializedName("")
    public Double specialPrice;

    @SerializedName("")
    public String conditions;

    @SerializedName("")
    public String materialWindow;

    @SerializedName("")
    public String typeSewerage;

    @SerializedName("")
    public String srcLayout;
}
