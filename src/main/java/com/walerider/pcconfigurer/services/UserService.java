package com.walerider.pcconfigurer.services;

import com.walerider.pcconfigurer.entities.User;
import com.walerider.pcconfigurer.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User createUser(User user) {
        if(userRepository.findByEmail(user.getEmail()) != null){
            throw new RuntimeException("Пользователь с такой почтой уже существует");
        }
        return userRepository.save(user);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
