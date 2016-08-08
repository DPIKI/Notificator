/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpiki.notificator.network.gson;

/**
 *
 * @author maksim
 */
public class Infrastructure {
    
    private Long id;
    private String infrastructure;
  //  private Pivot pivot;

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
     * @return the infrastructure
     */
    public String getInfrastructure() {
        return infrastructure;
    }

    /**
     * @param infrastructure the infrastructure to set
     */
    public void setInfrastructure(String infrastructure) {
        this.infrastructure = infrastructure;
    }

    
}
