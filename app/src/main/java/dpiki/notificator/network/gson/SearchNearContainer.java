package dpiki.notificator.network.gson;

import com.google.gson.annotations.SerializedName;


public class SearchNearContainer {
    
    private Realestate realestate;
    @SerializedName("realestate_type")
    private RealestateInfo[] realestateTypeArray;
    private Photo[] photos;
    private String employe;

    public Realestate getRealestate() {
        return realestate;
    }

    public void setRealestate(Realestate realestate) {
        this.realestate = realestate;
    }

    public RealestateInfo[] getRealestateTypeArray() {
        return realestateTypeArray;
    }

    public void setRealestateTypeArray(RealestateInfo[] realestateType) {
        this.realestateTypeArray = realestateType;
    }
    
    
    public RealestateInfo getRealestateType() {
        return realestateTypeArray[0];
    }

    public void setRealestateType(RealestateInfo realestateType) {
        this.realestateTypeArray[0] = realestateType;
    }


    public Photo[] getPhotos() {
        return photos;
    }

    public void setPhotos(Photo[] photos) {
        this.photos = photos;
    }

    /**
     * @return the employe
     */
    public String getEmploye() {
        return employe;
    }

    /**
     * @param employe the employe to set
     */
    public void setEmploye(String employe) {
        this.employe = employe;
    }
}