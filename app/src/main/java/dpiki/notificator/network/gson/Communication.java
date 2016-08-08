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
public class Communication {

    private Long id;
    
    @SerializedName("communication")
    private String communication;
    
    @SerializedName("commercial_communication")
    private String commercialCommunication;

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
     * @return the communication
     */
    public String getCommunication() {
        return communication;
    }

    /**
     * @param communication the communication to set
     */
    public void setCommunication(String communication) {
        this.communication = communication;
    }

    /**
     * @return the commercialCommunication
     */
    public String getCommercialCommunication() {
        return commercialCommunication;
    }

    /**
     * @param commercialCommunication the commercialCommunication to set
     */
    public void setCommercialCommunication(String commercialCommunication) {
        this.commercialCommunication = commercialCommunication;
    }



}
