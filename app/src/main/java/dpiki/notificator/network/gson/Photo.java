package dpiki.notificator.network.gson;

import com.google.gson.annotations.SerializedName;



public class Photo {
    private Long id;
    @SerializedName("id_realestate")
    private Long idRealestate;
    private String filename;
    private String src;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdRealestate() {
        return idRealestate;
    }

    public void setIdRealestate(Long idRealestate) {
        this.idRealestate = idRealestate;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return "Photo{" + "id=" + id + ", idRealestate=" + idRealestate + ", filename=" + filename + ", src=" + src + '}';
    }
    
    
}
