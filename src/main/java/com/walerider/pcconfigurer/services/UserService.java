package com.walerider.pcconfigurer.services;

import com.walerider.pcconfigurer.entities.User;
import com.walerider.pcconfigurer.repositories.UserRepository;
import com.walerider.pcconfigurer.validation.exceptions.BadRequestException;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new BadRequestException("User with this username already exists");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("User with this email already exists");
        }
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void updateUser(@RequestBody User newUser, @PathVariable Long id) {
        userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    user.setPassword(newUser.getPassword());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new BadRequestException("User not found"));
    }
    public User getUserByUsername(@RequestParam String username, @RequestParam String password) {
        if(userRepository.existsByUsername(username)) {
            User userFound = userRepository.findByUsername(username);
            if(userFound.getPassword().equals(password)) {
                return userFound;
            }else{
                throw new BadRequestException("Wrong password");
            }
        }
        throw new BadRequestException("User not found");
    }
    public void deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("User not found"));
        userRepository.deleteById(id);
    }
}
