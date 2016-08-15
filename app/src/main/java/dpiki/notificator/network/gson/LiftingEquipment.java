/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpiki.notificator.network.gson;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author maksim
 */
public class LiftingEquipment {
    
    private Long id;
    @SerializedName("lifting_equipment")
    private String LiftingEquipment;

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
     * @return the idLiftingEquipments
     */
    public String getLiftingEquipment() {
        return LiftingEquipment;
    }

    /**
     * @param idLiftingEquipment the idLiftingEquipments to set
     */
    public void setIdLiftingEquipment(String liftingEquipment) {
        this.LiftingEquipment = liftingEquipment;
    }
    
}
