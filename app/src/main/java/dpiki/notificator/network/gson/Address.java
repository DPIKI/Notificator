package dpiki.notificator.network.gson;

import com.google.gson.annotations.SerializedName;

public class Address {

    private Long id;
    private String street;
    private String house;
    private String corps;
    private String apartment;
    private Double longitude;
    private Double latitude;
    @SerializedName("id_district")
    private Long idDistrict;
    @SerializedName("type_district")
    private TypeDistrict typeDistrict;
    @SerializedName("id_administrative_district")
    private long idAdministrativeDistrict;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getCorps() {
        return corps;
    }

    public void setCorps(String corps) {
        this.corps = corps;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Long getIdDistrict() {
        return idDistrict;
    }

    public void setIdDistrict(Long idDistrict) {
        this.idDistrict = idDistrict;
    }

    public TypeDistrict getTypeDistrict() {
        return typeDistrict;
    }

    public void setTypeDistrict(TypeDistrict typeDistrict) {
        this.typeDistrict = typeDistrict;
    }

    @Override
    public String toString() {
        return "Address{" + "id=" + id + ", street=" + street + ", house=" + house + ", corps=" + corps + ", apartment=" + apartment + ", longitude=" + longitude + ", latitude=" + latitude + ", idDistrict=" + idDistrict + ", typeDistrict=" + typeDistrict.toString() + ",idAdministrativeDistrict=" + getIdAdministrativeDistrict() + '}';
    }

    /**
     * @return the idAdministrativeDistrict
     */
    public long getIdAdministrativeDistrict() {
        return idAdministrativeDistrict;
    }

    /**
     * @param idAdministrativeDistrict the idAdministrativeDistrict to set
     */
    public void setIdAdministrativeDistrict(long idAdministrativeDistrict) {
        this.idAdministrativeDistrict = idAdministrativeDistrict;
    }
}
