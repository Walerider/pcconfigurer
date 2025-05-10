package com.walerider.pcconfigurer.Controllers;

import com.walerider.pcconfigurer.entities.User;
import com.walerider.pcconfigurer.services.UserService;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;
    Session session;
    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;

    }
    @GetMapping("/all")
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userService.createUser(user);
        return new ResponseEntity<>("Client add", HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@RequestBody User user,@PathVariable Long id) {
        userService.updateUser(user,id);
        return new ResponseEntity<>("Client update", HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("Client with " + id + " deleted", HttpStatus.OK);
    }
}
