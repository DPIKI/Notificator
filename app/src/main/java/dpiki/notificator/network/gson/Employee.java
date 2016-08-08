package dpiki.notificator.network.gson;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class Employee {

    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private Date birth;
    private String email;
    @SerializedName(value = "phone_inner")
    private String phoneInner;
    @SerializedName(value = "phone_mob")
    private String phoneMob;
    @SerializedName(value = "phone_home")
    private String phoneHome;
    private String adress;
    @SerializedName(value = "id_subdivision")
    private Long idSubdivision;
    @SerializedName(value = "id_department")
    private Long idDepartment;
    @SerializedName(value = "id_post")
    private Long idPost;
    @SerializedName(value = "id_headhunter")
    private Long idHeadhunter;
    @SerializedName(value = "head_subdivison")
    private Integer headSubdivison;
    @SerializedName(value = "head_office")
    private Integer headOffice;
    @SerializedName(value = "head_department")
    private Integer headDepartment;
    @SerializedName(value = "mentor_agree")
    private Integer mentorAgree;
    private String src;
    @SerializedName(value = "full_name")
    private String fullName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneInner() {
        return phoneInner;
    }

    public void setPhoneInner(String phoneInner) {
        this.phoneInner = phoneInner;
    }

    public String getPhoneMob() {
        return phoneMob;
    }

    public void setPhoneMob(String phoneMob) {
        this.phoneMob = phoneMob;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public void setPhoneHome(String phoneHome) {
        this.phoneHome = phoneHome;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Long getIdSubdivision() {
        return idSubdivision;
    }

    public void setIdSubdivision(Long idSubdivision) {
        this.idSubdivision = idSubdivision;
    }

    public Long getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Long idDepartment) {
        this.idDepartment = idDepartment;
    }

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }

    public Long getIdHeadhunter() {
        return idHeadhunter;
    }

    public void setIdHeadhunter(Long idHeadhunter) {
        this.idHeadhunter = idHeadhunter;
    }

    public Integer getHeadSubdivison() {
        return headSubdivison;
    }

    public void setHeadSubdivison(Integer headSubdivison) {
        this.headSubdivison = headSubdivison;
    }

    public Integer getHeadOffice() {
        return headOffice;
    }

    public void setHeadOffice(Integer headOffice) {
        this.headOffice = headOffice;
    }

    public Integer getHeadDepartment() {
        return headDepartment;
    }

    public void setHeadDepartment(Integer headDepartment) {
        this.headDepartment = headDepartment;
    }

    public Integer getMentorAgree() {
        return mentorAgree;
    }

    public void setMentorAgree(Integer mentorAgree) {
        this.mentorAgree = mentorAgree;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", surname=" + surname + ", name=" + name + ", patronymic=" + patronymic + ", birth=" + birth + ", email=" + email + ", phoneInner=" + phoneInner + ", phoneMob=" + phoneMob + ", phoneHome=" + phoneHome + ", adress=" + adress + ", idSubdivision=" + idSubdivision + ", idDepartment=" + idDepartment + ", idPost=" + idPost + ", idHeadhunter=" + idHeadhunter + ", headSubdivison=" + headSubdivison + ", headOffice=" + headOffice + ", headDepartment=" + headDepartment + ", mentorAgree=" + mentorAgree + ", src=" + src + ", fullName=" + fullName + '}';
    }
}
