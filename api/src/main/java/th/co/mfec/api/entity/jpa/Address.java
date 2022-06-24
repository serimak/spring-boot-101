package th.co.mfec.api.entity.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS")
public class Address {
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "line_1")
    private String line1;
    @Column(name = "line_2")
    private String line2;
    @Column(name = "postcode")
    private String postcode;
    @Column(name = "type")
    private String type;
    @Column(name = "prefer")
    private String prefer;
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
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getLine1() {
        return line1;
    }
    public void setLine1(String line1) {
        this.line1 = line1;
    }
    public String getLine2() {
        return line2;
    }
    public void setLine2(String line2) {
        this.line2 = line2;
    }
    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getPrefer() {
        return prefer;
    }
    public void setPrefer(String prefer) {
        this.prefer = prefer;
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
