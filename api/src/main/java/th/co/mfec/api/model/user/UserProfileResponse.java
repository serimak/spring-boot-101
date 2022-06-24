package th.co.mfec.api.model.user;

import java.util.Date;

public class UserProfileResponse {
    
    private String firstNameTh;
    private String lastNameTh;
    private String firstNameEn;
    private String lastNameEn;
    private String mobileNumber;
    private Date birthDate;

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
    
}
