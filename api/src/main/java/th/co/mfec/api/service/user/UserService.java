package th.co.mfec.api.service.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.mfec.api.entity.jpa.User;
import th.co.mfec.api.model.user.UserRequest;
import th.co.mfec.api.model.user.UserResponse;
import th.co.mfec.api.repository.jpa.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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
}
