package com.walerider.pcconfigurer.Controllers;

import com.walerider.pcconfigurer.entities.User;
import com.walerider.pcconfigurer.services.UserService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/create")
    public String createUser(@RequestBody User user) {
        userService.createUser(user);
        return "Client add";
    }

    @PutMapping("/update/{id}")
    public String updateUser(@RequestBody User user, @PathVariable Long id) {
        userService.updateUser(user, id);
        return "Client update";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "Client with id " + id + " deleted";
    }
}
