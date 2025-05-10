package com.walerider.pcconfigurer;

import com.walerider.pcconfigurer.entities.User;
import com.walerider.pcconfigurer.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void testSaveUser () {
        User user = new User(111L,"aa","asdasf","aaaaaaaaaa");
        System.out.println(user.getEmail());
        userService.createUser(user);
    }
}
