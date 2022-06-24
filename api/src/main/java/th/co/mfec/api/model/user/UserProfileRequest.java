package th.co.mfec.api.model.user;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserProfileRequest {
    
    @NotEmpty(message = "The first name thai is required.")
    private String firstNameTh;
    @NotEmpty(message = "The last name thai is required.")
    private String lastNameTh;
    @NotEmpty(message = "The first name english is required.")
    private String firstNameEn;
    @NotEmpty(message = "The last name english is required.")
    private String lastNameEn;
    @NotEmpty(message = "The mobile number is required.")
    private String mobileNumber;
    @NotNull(message = "The birth date is required.")
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

