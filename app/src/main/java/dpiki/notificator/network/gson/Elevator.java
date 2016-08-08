package dpiki.notificator.network.gson;


public class Elevator {
                   
    private Long id;
    private String elevator;
   // private Pivot pivot;

    @Override
    public String toString()
    {
      return "Infrastructure:{"+"id="+id+",elevator="+elevator+"}";
    }
    
    
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
     * @return the elevator
     */
    public String getElevator() {
        return elevator;
    }

    /**
     * @param elevator the elevator to set
     */
    public void setElevator(String elevator) {
        this.elevator = elevator;
    }

    
    
}
