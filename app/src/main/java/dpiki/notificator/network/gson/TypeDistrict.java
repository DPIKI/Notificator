package dpiki.notificator.network.gson;

import com.google.gson.annotations.SerializedName;

public class TypeDistrict {

    private Long id;
    @SerializedName("id_city")
    private Long idCity;
    private String district;
    private String subdistrict;
    private City city;

    public TypeDistrict() {
    }

    public TypeDistrict(Long id, String district, String subdistrict) {
        this.id = id;
        this.district = district;
        this.subdistrict = subdistrict;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCity() {
        return idCity;
    }

    public void setIdCity(Long idCity) {
        this.idCity = idCity;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSubdistrict() {
        return subdistrict;
    }

    public void setSubdistrict(String subdistrict) {
        this.subdistrict = subdistrict;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "TypeDistrict{" + "id=" + id + ", idCity=" + idCity + ", district=" + district + ", subdistrict=" + subdistrict + ", city=" + city.toString() + '}';
    }
}
