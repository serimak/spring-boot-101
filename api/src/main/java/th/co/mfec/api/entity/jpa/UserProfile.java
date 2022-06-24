package th.co.mfec.api.entity.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_PROFILE")
public class UserProfile {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name_th")
    private String firstNameTh;
    @Column(name = "last_name_th")
    private String lastNameTh;
    @Column(name = "first_name_en")
    private String firstNameEn;
    @Column(name = "last_name_en")
    private String lastNameEn;
    @Column(name = "mobile_number")
    private String mobileNumber;
    @Column(name = "birth_date")
    private Date birthDate;
    @Column(name = "delete_flag")
    private String deleteFlag;
    @Column(name = "created_by")
    private Integer createdBy;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_by")
    private Integer updatedBy;
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "deleted_by")
    private Integer deletedBy;
    @Column(name = "deleted_at")
    private Date deletedAt;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getFirstNameTh() {
        return firstNameTh;
    }
    public void setFirstNameTh(String firstNameTh) {
        this.firstNameTh = firstNameTh;
    }
    public String getLastNameTh() {
        return lastNameTh;
    }
    public void setLastNameTh(String lastNameTh) {
        this.lastNameTh = lastNameTh;
    }
    public String getFirstNameEn() {
        return firstNameEn;
    }
    public void setFirstNameEn(String firstNameEn) {
        this.firstNameEn = firstNameEn;
    }
    public String getLastNameEn() {
        return lastNameEn;
    }
    public void setLastNameEn(String lastNameEn) {
        this.lastNameEn = lastNameEn;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public String getDeleteFlag() {
        return deleteFlag;
    }
    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    public Integer getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public Integer getUpdatedBy() {
        return updatedBy;
    }
    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    public Integer getDeletedBy() {
        return deletedBy;
    }
    public void setDeletedBy(Integer deletedBy) {
        this.deletedBy = deletedBy;
    }
    public Date getDeletedAt() {
        return deletedAt;
    }
    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    
}
