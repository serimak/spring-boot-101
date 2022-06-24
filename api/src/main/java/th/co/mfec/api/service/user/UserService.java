package th.co.mfec.api.service.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import th.co.mfec.api.constant.StatusCode;
import th.co.mfec.api.entity.jpa.Address;
import th.co.mfec.api.entity.jpa.User;
import th.co.mfec.api.entity.jpa.UserProfile;
import th.co.mfec.api.exception.BaseException;
import th.co.mfec.api.model.user.AddressRequest;
import th.co.mfec.api.model.user.AddressResponse;
import th.co.mfec.api.model.user.UserAddressRequest;
import th.co.mfec.api.model.user.UserAddressResponse;
import th.co.mfec.api.model.user.UserAuthenRequest;
import th.co.mfec.api.model.user.UserAuthenResponse;
import th.co.mfec.api.model.user.UserProfileAddressesResponse;
import th.co.mfec.api.model.user.UserProfileRequest;
import th.co.mfec.api.model.user.UserProfileResponse;
import th.co.mfec.api.model.user.UserRegisterRequest;
import th.co.mfec.api.model.user.UserRegisterResponse;
import th.co.mfec.api.model.user.UserRequest;
import th.co.mfec.api.model.user.UserResponse;
import th.co.mfec.api.repository.jpa.AddressRepository;
import th.co.mfec.api.repository.jpa.UserProfileRepository;
import th.co.mfec.api.repository.jpa.UserRepository;
import th.co.mfec.api.security.util.JwtUtil;

@Service
public class UserService {

    @Autowired
    public JwtUtil jwtUtil;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    public UserResponse createUser(UserRequest userRequest){
        
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setIsEnabled("Y");
        user.setIsLocked("N");
        user.setExpiredDate(new Date());
        user.setCreatedBy(1);
        user.setCreatedAt(new Date());
        user.setDeleteFlag("N");
        userRepository.save(user);

        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setExpiredDate(user.getExpiredDate());

        return userResponse;
    }

    public UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest) {
        User user = new User();
        user.setUsername(userRegisterRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        user.setIsEnabled("Y");
        user.setIsLocked("N");
        user.setExpiredDate(new Date());
        user.setCreatedBy(1);
        user.setCreatedAt(new Date());
        user.setDeleteFlag("N");
        userRepository.save(user);

        UserRegisterResponse userResponse = new UserRegisterResponse();
        userResponse.setUserId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setExpiredDate(user.getExpiredDate());

        return userResponse;
    }

    public UserAuthenResponse authenUser(UserAuthenRequest userAuthenRequest) {
        User user = userRepository.findByUsername(userAuthenRequest.getUsername());
        if(user == null){
            throw new BaseException(HttpStatus.UNAUTHORIZED, StatusCode.ERR_CODE_401, StatusCode.ERR_DESC_401);
        }
        if(!(passwordEncoder.matches(userAuthenRequest.getPassword(), user.getPassword()))){
            throw new BaseException(HttpStatus.UNAUTHORIZED, StatusCode.ERR_CODE_401, StatusCode.ERR_DESC_401);
        }

        String token = jwtUtil.generateToken(user.getUsername());
        UserAuthenResponse userAuthenResponse = new UserAuthenResponse();
        userAuthenResponse.setUserId(user.getId());
        userAuthenResponse.setUsername(user.getUsername());
        userAuthenResponse.setToken(token);

        return userAuthenResponse;
    }

    public UserAuthenResponse refreshToken() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(username);

        String token = jwtUtil.generateToken(user.getUsername());
        UserAuthenResponse userAuthenResponse = new UserAuthenResponse();
        userAuthenResponse.setUserId(user.getId());
        userAuthenResponse.setUsername(user.getUsername());
        userAuthenResponse.setToken(token);

        return userAuthenResponse;
    }

    public UserProfileResponse updateUserProfile(UserProfileRequest userProfileRequest) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(username);

        UserProfile userProfile = new UserProfile();
        userProfile.setUser(user);
        userProfile.setFirstNameTh(userProfileRequest.getFirstNameTh());
        userProfile.setLastNameTh(userProfileRequest.getLastNameTh());
        userProfile.setFirstNameEn(userProfileRequest.getFirstNameEn());
        userProfile.setLastNameEn(userProfileRequest.getLastNameEn());
        userProfile.setMobileNumber(userProfileRequest.getMobileNumber());
        userProfile.setBirthDate(userProfileRequest.getBirthDate());
        userProfile.setDeleteFlag("N");
        userProfile.setCreatedBy(user.getId());
        userProfile.setCreatedAt(new Date());
        userProfileRepository.save(userProfile);

        UserProfileResponse userProfileResponse = new UserProfileResponse();
        userProfileResponse.setFirstNameTh(userProfile.getFirstNameTh());
        userProfileResponse.setLastNameTh(userProfile.getLastNameTh());
        userProfileResponse.setFirstNameEn(userProfile.getFirstNameEn());
        userProfileResponse.setLastNameEn(userProfile.getLastNameEn());
        userProfileResponse.setMobileNumber(userProfile.getMobileNumber());
        userProfileResponse.setBirthDate(userProfile.getBirthDate());
        return userProfileResponse;
    }

    @Transactional
    public UserAddressResponse updateUserAddress(UserAddressRequest userAddressRequest) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(username);

        List<Address> addresses = new ArrayList<Address>();
        Address address = null;
        for(AddressRequest addressRequest : userAddressRequest.getAddresses()){
            address = new Address();
            address.setUser(user);
            address.setLine1(addressRequest.getLine1());
            address.setLine2(addressRequest.getLine2());
            address.setPostcode(addressRequest.getPostcode());
            address.setType(addressRequest.getType());
            address.setPrefer(addressRequest.getPrefer());
            address.setDeleteFlag("N");
            address.setCreatedBy(user.getId());
            address.setCreatedAt(new Date());
            addresses.add(address);
        }
        addressRepository.saveAll(addresses);

        UserAddressResponse userAddressResponse = new UserAddressResponse();
        List<AddressResponse> addressResponses = new ArrayList<AddressResponse>();
        AddressResponse addressResponse = null;
        for(Address addr : addresses){
            addressResponse = new AddressResponse();
            addressResponse.setLine1(addr.getLine1());
            addressResponse.setLine2(addr.getLine2());
            addressResponse.setPostcode(addr.getPostcode());
            addressResponse.setType(addr.getType());
            addressResponse.setPrefer(addr.getPrefer());
            addressResponses.add(addressResponse);
        }
        userAddressResponse.setAddresses(addressResponses);
        return userAddressResponse;
    }

    public UserProfileAddressesResponse getUserProfileAddresses() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(username);

        UserProfileAddressesResponse userProfileAddressesResponse = new UserProfileAddressesResponse();
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setExpiredDate(user.getExpiredDate());
        userProfileAddressesResponse.setUser(userResponse);

        UserProfileResponse userProfileResponse = new UserProfileResponse();
        if(user.getUserProfile() != null){
            userProfileResponse.setFirstNameTh(user.getUserProfile().getFirstNameTh());
            userProfileResponse.setLastNameTh(user.getUserProfile().getLastNameTh());
            userProfileResponse.setFirstNameEn(user.getUserProfile().getFirstNameEn());
            userProfileResponse.setLastNameEn(user.getUserProfile().getLastNameEn());
            userProfileResponse.setMobileNumber(user.getUserProfile().getMobileNumber());
            userProfileResponse.setBirthDate(user.getUserProfile().getBirthDate());
        }
        userProfileAddressesResponse.setProfile(userProfileResponse);

        UserAddressResponse userAddressResponse = new UserAddressResponse();
        List<AddressResponse> addresses = new ArrayList<AddressResponse>();
        AddressResponse addressResponse = null;
        if(user.getAddresses() != null){
            for(Address address : user.getAddresses()){
                addressResponse = new AddressResponse();
                addressResponse.setLine1(address.getLine1());
                addressResponse.setLine2(address.getLine2());
                addressResponse.setPostcode(address.getPostcode());
                addressResponse.setType(address.getType());
                addressResponse.setPrefer(address.getPrefer());
                addresses.add(addressResponse);
            }
        }
        userAddressResponse.setAddresses(addresses);
        userProfileAddressesResponse.setAddresses(userAddressResponse);

        return userProfileAddressesResponse;
    }
}
