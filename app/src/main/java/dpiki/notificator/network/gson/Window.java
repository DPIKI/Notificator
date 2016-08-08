package dpiki.notificator.network.gson;


public class Window {
    private Long id;
    private String windows;
  //  private Pivot pivot;

    @Override
    public String toString()
    {
      return "Infrastructure:{"+"id="+id+",elevator="+windows+",pivot="+"}";
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
     * @return the window
     */
    public String getElevator() {
        return windows;
    }

    /**
     * @param elevator the window to set
     */
    public void setElevator(String elevator) {
        this.windows = elevator;
    }

}
