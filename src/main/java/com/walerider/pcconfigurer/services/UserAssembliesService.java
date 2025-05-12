package com.walerider.pcconfigurer.services;

import com.walerider.pcconfigurer.DTO.UserAssemblyDTO;
import com.walerider.pcconfigurer.Mappers.UserAssemblyMapper;
import com.walerider.pcconfigurer.entities.User;
import com.walerider.pcconfigurer.entities.UserAssembly;
import com.walerider.pcconfigurer.repositories.UserAssembliesRepository;
import com.walerider.pcconfigurer.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Transactional
public class UserAssembliesService {
    private final UserAssembliesRepository userAssembliesRepository;
    private final UserRepository userRepository;
    private final UserAssemblyMapper mapper;
    @Autowired
    public UserAssembliesService(UserAssembliesRepository userAssembliesRepository, UserRepository userRepository, UserAssemblyMapper mapper) {
        this.userAssembliesRepository = userAssembliesRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }
    public List<UserAssemblyDTO> getAllUserAssemblies() {
        return mapper.toDtoList(userAssembliesRepository.findAll());
    }
    public List<UserAssemblyDTO> getAllUserAssembliesDTOByUserId(Long userId) {
        List<UserAssembly> userAssemblies = userAssembliesRepository.findByUserId(userId);
        if (userAssemblies.isEmpty()) {
            return null;
        }
        return mapper.toDtoList(userAssemblies);
    }
    public void createUserAssemblies(@RequestBody UserAssemblyDTO request) {
        User user = userRepository
                .findById(request.getUserId())
                .orElseThrow(()-> new RuntimeException("User doesnt exist"));
        if(userAssembliesRepository.findByName(request.getName()) != null){
            throw new RuntimeException("Assembly with this name already exists");
        }
        userAssembliesRepository.save(new UserAssembly(request.getName(), user));
    }
    public void updateUserAssemblies(@RequestBody UserAssemblyDTO request, @PathVariable Long assemblyId) {
        userAssembliesRepository.findById(assemblyId)
                .map(assembly -> {
                    assembly.setName(request.getName());
                    assembly.setUser(userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User doesnt exist")));
                    return userAssembliesRepository.save(assembly);
                }).orElseThrow(() -> new RuntimeException("Assembly not found"));
    }
}
