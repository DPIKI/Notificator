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
public class CommercialPayment {

    private Long id;
    @SerializedName("commercial_payment")
    private String commercialPayment;

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
     * @return the commercialPayment
     */
    public String getCommercialPayment() {
        return commercialPayment;
    }

    /**
     * @param commercialPayment the commercialPayment to set
     */
    public void setCommercialPayment(String commercialPayment) {
        this.commercialPayment = commercialPayment;
    }

  
}
