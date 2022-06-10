package th.co.mfec.api.model.user;

import java.util.Date;

public class UserResponse {
    
    private Integer id;
    private String username;
    private Date expiredDate;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Date getExpiredDate() {
        return expiredDate;
    }
    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }
    
}
