package dpiki.notificator.network.gson;

import com.google.gson.annotations.SerializedName;


public class RealestateContainer {
    
    private Realestate[] realestate;
    @SerializedName("realestate_type")
    private RealestateInfo[] realestateType;
    private Address[] address;
    private Photo[] photos;

    public Realestate[] getRealestate() {
        return realestate;
    }

    public void setRealestate(Realestate[] realestate) {
        this.realestate = realestate;
    }

    public RealestateInfo[] getRealestateType() {
        return realestateType;
    }

    public void setRealestateType(RealestateInfo[] realestateType) {
        this.realestateType = realestateType;
    }

    public Address[] getAddress() {
        return address;
    }

    public void setAddress(Address[] address) {
        this.address = address;
    }

    public Photo[] getPhotos() {
        return photos;
    }

    public void setPhotos(Photo[] photos) {
        this.photos = photos;
    }
    
}
