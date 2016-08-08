package dpiki.notificator.network.gson;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class Realestate {

    private Long id;
    @SerializedName("realestate_instance_id")
    private Long realestateInstanceId;
    @SerializedName("realestate_instance_type")
    private String realestateInstanceType;
    @SerializedName("id_address")
    private Long idAddress;
    @SerializedName("id_status_realestate")
    private Long idStatusRealestate;
    @SerializedName("is_exclusive")
    private Integer isExclusive;
    @SerializedName("is_firm_contact_person")
    private Integer isFirmContactPerson;
    @SerializedName("date_create")
    private Date dateCreate;
    private String comment;
    @SerializedName("text_website")
    private String textWebsite;
    @SerializedName("id_source")
    private Long idSource;
    @SerializedName("contact_note")
    private String contactNote;
    @SerializedName("distance_stops")
    private String distanceStops;
    @SerializedName("cover_name")
    private String coverName;
    @SerializedName("cover_src")
    private String coverSrc;
    @SerializedName("id_employee_assigned")
    private Long idEmployeeAssigned;
    @SerializedName("id_employee_create")
    private Long idEmployeeCreate;
    @SerializedName("realestate_number")
    private String realestateNumber;
    @SerializedName("created_at")
    private Date createdAt;
    @SerializedName("updated_at")
    private Date updatedAt;
    private Address address;
    private Contact[] contacts;
    private Infrastructure[] infrastructure;
    @SerializedName("year_build")
    private Integer yearBuild;
    @SerializedName("opensale_begin")
    private Date opensaleBegin;
    @SerializedName("opensale_end")
    private Date opensaleEnd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRealestateInstanceId() {
        return realestateInstanceId;
    }

    public void setRealestateInstanceId(Long realestateInstanceId) {
        this.realestateInstanceId = realestateInstanceId;
    }

    public String getRealestateInstanceType() {
        return realestateInstanceType;
    }

    public void setRealestateInstanceType(String realestateInstanceType) {
        this.realestateInstanceType = realestateInstanceType;
    }

    public Long getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Long idAddress) {
        this.idAddress = idAddress;
    }

    public Long getIdStatusRealestate() {
        return idStatusRealestate;
    }

    public void setIdStatusRealestate(Long idStatusRealestate) {
        this.idStatusRealestate = idStatusRealestate;
    }

    public Integer getIsExclusive() {
        return isExclusive;
    }

    public void setIsExclusive(Integer isExclusive) {
        this.isExclusive = isExclusive;
    }

    public Integer getIsFirmContactPerson() {
        return isFirmContactPerson;
    }

    public void setIsFirmContactPerson(Integer isFirmContactPerson) {
        this.isFirmContactPerson = isFirmContactPerson;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTextWebsite() {
        return textWebsite;
    }

    public void setTextWebsite(String textWebsite) {
        this.textWebsite = textWebsite;
    }

    public Long getIdSource() {
        return idSource;
    }

    public void setIdSource(Long idSource) {
        this.idSource = idSource;
    }

    public String getContactNote() {
        return contactNote;
    }

    public void setContactNote(String contactNote) {
        this.contactNote = contactNote;
    }

    public String getDistanceStops() {
        return distanceStops;
    }

    public void setDistanceStops(String distanceStops) {
        this.distanceStops = distanceStops;
    }

    public String getCoverName() {
        return coverName;
    }

    public void setCoverName(String coverName) {
        this.coverName = coverName;
    }

    public String getCoverSrc() {
        return coverSrc;
    }

    public void setCoverSrc(String coverSrc) {
        this.coverSrc = coverSrc;
    }

    public Long getIdEmployeeAssigned() {
        return idEmployeeAssigned;
    }

    public void setIdEmployeeAssigned(Long idEmployeeAssigned) {
        this.idEmployeeAssigned = idEmployeeAssigned;
    }

    public Long getIdEmployeeCreate() {
        return idEmployeeCreate;
    }

    public void setIdEmployeeCreate(Long idEmployeeCreate) {
        this.idEmployeeCreate = idEmployeeCreate;
    }

//    @SerializedName("realestate_number")
//     private String realestateNumber;
//     @SerializedName("created_at")
//     private Date createdAt;
//     @SerializedName("updated_at")
//     private Date updatedAt;
//     private Address address;
//     private Contact[] contacts;
//     private Infrastructure[] infrastructure;
    @Override
    public String toString() {
        return "Realestate{" + "id=" + id + ", realestateInstanceId=" + realestateInstanceId + ", realestateInstanceType=" + realestateInstanceType + ", idAddress=" + idAddress + ", idStatusRealestate=" + idStatusRealestate + ", isExclusive=" + isExclusive + ", isFirmContactPerson=" + isFirmContactPerson + ", dateCreate=" + dateCreate + ", comment=" + comment + ", textWebsite=" + textWebsite + ", idSource=" + idSource + ", contactNote=" + contactNote + ", distanceStops=" + distanceStops + ", coverName=" + coverName + ", coverSrc=" + coverSrc + ", idEmployeeAssigned=" + idEmployeeAssigned + ", idEmployeeCreate=" + idEmployeeCreate
                + ",realestateNumber=" + realestateNumber
                + ",createdAt="
                + createdAt
                + ",updatedAt="
                + updatedAt
                + ",address=" + address.toString() + ",contacts=" + Helper.ArrayToString(contacts) + ",infrastructure=" + Helper.ArrayToString(infrastructure)
                + '}';
    }

    /**
     * @return the realestateNumber
     */
    public String getRealestateNumber() {
        return realestateNumber;
    }

    /**
     * @param realestateNumber the realestateNumber to set
     */
    public void setRealestateNumber(String realestateNumber) {
        this.realestateNumber = realestateNumber;
    }

    /**
     * @return the createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return the updatedAt
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt the updatedAt to set
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @return the contacts
     */
    public Contact[] getContacts() {
        return contacts;
    }

    /**
     * @param contacts the contacts to set
     */
    public void setContacts(Contact[] contacts) {
        this.contacts = contacts;
    }

    /**
     * @return the infrastructure
     */
    public Infrastructure[] getInfrastructure() {
        return infrastructure;
    }

    /**
     * @param infrastructure the infrastructure to set
     */
    public void setInfrastructure(Infrastructure[] infrastructure) {
        this.infrastructure = infrastructure;
    }

    /**
     * @return the yearBuild
     */
    public Integer getYearBuild() {
        return yearBuild;
    }

    /**
     * @param yearBuild the yearBuild to set
     */
    public void setYearBuild(Integer yearBuild) {
        this.yearBuild = yearBuild;
    }

    /**
     * @return the opensaleBegin
     */
    public Date getOpensaleBegin() {
        return opensaleBegin;
    }

    /**
     * @param opensaleBegin the opensaleBegin to set
     */
    public void setOpensaleBegin(Date opensaleBegin) {
        this.opensaleBegin = opensaleBegin;
    }

    /**
     * @return the opensaleEnd
     */
    public Date getOpensaleEnd() {
        return opensaleEnd;
    }

    /**
     * @param opensaleEnd the opensaleEnd to set
     */
    public void setOpensaleEnd(Date opensaleEnd) {
        this.opensaleEnd = opensaleEnd;
    }
}
