package com.walerider.pcconfigurer.Controllers;

import com.walerider.pcconfigurer.entities.User;
import com.walerider.pcconfigurer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private final UserService userService;
    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}
