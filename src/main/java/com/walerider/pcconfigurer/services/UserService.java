package com.walerider.pcconfigurer.services;

import com.walerider.pcconfigurer.entities.User;
import com.walerider.pcconfigurer.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(@RequestBody User user) {
        if(userRepository.findByEmail(user.getEmail()) != null){
            throw new RuntimeException("User with this email already exists");
        }
        return userRepository.save(user);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userRepository
                .findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    user.setPassword(newUser.getPassword());
                    return userRepository.save(user);
                })
                .orElseGet(() ->{
                    throw new RuntimeException("User not found");
                });
    }
    public void deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseGet(() ->{
                            throw new RuntimeException("User not found");
                        });
        userRepository.deleteById(id);
    }
}
