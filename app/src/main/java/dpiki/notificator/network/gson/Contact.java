package dpiki.notificator.network.gson;

import com.google.gson.annotations.SerializedName;

public class Contact {
    
//    
//                     "id": 28,
//                    "contact_person": "\u041a\u043e\u043d\u0442\u0430\u043a\u0442\u043d\u043e\u0435 \u041b\u0438\u0446\u043e \u041a\u043f",
//                    "phone": "+7 (123) 654-78-90",
//                    "recieveSMS": 0,
//                    "email": "aaa@example.com",
//                    "pivot": {
//                        "id_realestate": 47,
//                        "id_contacts": 28
    
    private Long id;
    @SerializedName("contact_person")
    private String contactPerson;
    private String phone;
    private Integer recieveSMS;
    private String email;
  //  private Pivot pivot;
    
    @Override
    public String toString()
    {
      return "Contact{"+"id="+id+",contactPerson="+contactPerson+",phone="+phone+",recieveSMS="+recieveSMS+",email="+email+"}";
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
     * @return the contactPerson
     */
    public String getContactPerson() {
        return contactPerson;
    }

    /**
     * @param contactPerson the contactPerson to set
     */
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the recieveSMS
     */
    public Integer getRecieveSMS() {
        return recieveSMS;
    }

    /**
     * @param recieveSMS the recieveSMS to set
     */
    public void setRecieveSMS(Integer recieveSMS) {
        this.recieveSMS = recieveSMS;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }   
}
