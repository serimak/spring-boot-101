package th.co.mfec.api.service.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import th.co.mfec.api.constant.StatusCode;
import th.co.mfec.api.entity.jpa.User;
import th.co.mfec.api.exception.BaseException;
import th.co.mfec.api.model.user.UserAuthenRequest;
import th.co.mfec.api.model.user.UserAuthenResponse;
import th.co.mfec.api.model.user.UserRegisterRequest;
import th.co.mfec.api.model.user.UserRegisterResponse;
import th.co.mfec.api.model.user.UserRequest;
import th.co.mfec.api.model.user.UserResponse;
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
}
