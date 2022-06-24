package th.co.mfec.api.model.user;

public class AddressRequest {
    
    private String line1;
    private String line2;
    private String postcode;
    private String type;
    private String prefer;

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
    
}
