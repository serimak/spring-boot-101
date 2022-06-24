package th.co.mfec.api.model.user;

public class UserProfileAddressesResponse {

    private UserResponse user;
    private UserProfileResponse profile;
    private UserAddressResponse addresses;
    
    public UserResponse getUser() {
        return user;
    }
    public void setUser(UserResponse user) {
        this.user = user;
    }
    public UserProfileResponse getProfile() {
        return profile;
    }
    public void setProfile(UserProfileResponse profile) {
        this.profile = profile;
    }
    public UserAddressResponse getAddresses() {
        return addresses;
    }
    public void setAddresses(UserAddressResponse addresses) {
        this.addresses = addresses;
    }
    
}
