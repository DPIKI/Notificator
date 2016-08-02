package dpiki.notificator.data.requirements;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by prog1 on 02.08.2016.
 */
public class CommercialReq {

    @SerializedName("")
    Integer id;

    @SerializedName("")
    String name;

    @SerializedName("")
    List<String> typeProfile;

    @SerializedName("")
    String city;

    @SerializedName("")
    List<String> district;

    @SerializedName("")
    Integer firm;

    @SerializedName("")
    Integer photoOnly;

    @SerializedName("")
    String objectSelect;

    @SerializedName("")
    Double totalAreaLow;

    @SerializedName("")
    Double totalAreaHigh;

    @SerializedName("")
    Double hallAreaLow;

    @SerializedName("")
    Double hallAreaHigh;

    @SerializedName("")
    Double landAreaLow;

    @SerializedName("")
    Double landAreaHigh;

    @SerializedName("")
    Double rentAreaLow;

    @SerializedName("")
    Double rentAreaHigh;

    @SerializedName("")
    String typeCommunication;

    @SerializedName("")
    Integer hasElevetor;

    @SerializedName("")
    List<String> typeLiftingEquipment;

    @SerializedName("")
    Double sellPriceLow;

    @SerializedName("")
    Double sellPriceHigh;

    @SerializedName("")
    Double sellPriceSquareMeterLow;

    @SerializedName("")
    Double sellPriceSquareMeterHigh;

    @SerializedName("")
    Double rentalPriceLow;

    @SerializedName("")
    Double rentalPriceHigh;

    @SerializedName("")
    Double rentalPriceSquareMeterLow;

    @SerializedName("")
    Double rentalPriceSquareMeterHigh;
}
