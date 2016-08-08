/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpiki.notificator.network.gson;
import com.google.gson.annotations.SerializedName;

public class TypeRent {
    
    private Long id;
    @SerializedName("type_rent")
    private String typeRent;
   // private Pivot pivot;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the typeRent
     */
    public String getTypeRent() {
        return typeRent;
    }

    /**
     * @param typeRent the typeRent to set
     */
    public void setTypeRent(String typeRent) {
        this.typeRent = typeRent;
    }


    
}
