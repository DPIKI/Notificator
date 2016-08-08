package dpiki.notificator.network.gson;

import com.google.gson.annotations.SerializedName;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author maksim
 */
public class AdministrativeDistrict {

    @SerializedName("id_city")
    private Long idCity;
    @SerializedName("administrative_district")
    private String administrativeDistrict;

    public AdministrativeDistrict() {
    }

    public AdministrativeDistrict(Long idCity, String administrativeDistrict) {
        this.setIdCity(idCity);
        this.setAdministrative_district(administrativeDistrict);
    }

    @Override
    public String toString() {
        return "AdministrativeDistrict{" + "idCity=" + idCity + ",administrativeDistrict=" + administrativeDistrict + "}";
    }

    /**
     * @return the idCity
     */
    public Long getIdCity() {
        return idCity;
    }

    /**
     * @param idCity the idCity to set
     */
    public void setIdCity(Long idCity) {
        this.idCity = idCity;
    }

    /**
     * @return the administrativeDistrict
     */
    public String getAdministrative_district() {
        return administrativeDistrict;
    }

    /**
     * @param administrative_district the administrativeDistrict to set
     */
    public void setAdministrative_district(String administrative_district) {
        this.administrativeDistrict = administrative_district;
    }

}
