package th.co.mfec.api.model.common;

import java.util.Date;

public class StatusResponse {

    private String code;
    private String desc;
    private Date time;
    
    public StatusResponse(String code, String desc, Date time) {
        this.code = code;
        this.desc = desc;
        this.time = time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    
    
}
