package th.co.mfec.api.model.user;

import java.util.List;

public class UserAddressRequest {

    private List<AddressRequest> addresses;

    public List<AddressRequest> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressRequest> addresses) {
        this.addresses = addresses;
    }
    
    
}
