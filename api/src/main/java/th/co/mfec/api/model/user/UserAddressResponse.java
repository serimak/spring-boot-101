package th.co.mfec.api.model.user;

import java.util.List;

public class UserAddressResponse {
    
    private List<AddressResponse> addresses;

    public List<AddressResponse> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressResponse> addresses) {
        this.addresses = addresses;
    }
    
}
