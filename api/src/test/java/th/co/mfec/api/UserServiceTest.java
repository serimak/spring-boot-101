package th.co.mfec.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import th.co.mfec.api.model.user.UserRequest;
import th.co.mfec.api.model.user.UserResponse;
import th.co.mfec.api.service.user.UserService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    interface DataTestCreateUser {
        String username = "seri@mfec.co.th";
        String password = "P@ssw0rd";
    }

    @Order(1)
    @Test
    void testCreateUser(){
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername(DataTestCreateUser.username);
        userRequest.setPassword(DataTestCreateUser.password);
        UserResponse userResponse = userService.createUser(userRequest);

        Assertions.assertNotNull(userResponse);
        Assertions.assertNotNull(userResponse.getId());
        Assertions.assertEquals(DataTestCreateUser.username, userResponse.getUsername());
    }

    @Order(2)
    @Test
    void testSelectUser(){

    }
}
