package com.walerider.pcconfigurer.Controllers;

import com.walerider.pcconfigurer.DTO.UserAssemblyDTO;
import com.walerider.pcconfigurer.entities.UserAssembly;
import com.walerider.pcconfigurer.services.UserAssembliesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assemblies")
public class UserAssembliesRestController {
    private final UserAssembliesService userAssembliesService;

    public UserAssembliesRestController(UserAssembliesService userAssembliesService) {
        this.userAssembliesService = userAssembliesService;
    }

    @GetMapping("/all")
    public List<UserAssemblyDTO> getAllUsers() {
        return userAssembliesService.getAllUserAssemblies();
    }
    @GetMapping("/user/{id}")
    public List<UserAssemblyDTO> getAllUserAssembliesDTOById(@PathVariable Long id) {
        return userAssembliesService.getAllUserAssembliesDTOByUserId(id);
    }
    @PostMapping("/create")
    public ResponseEntity<String> createUserAssembly(@RequestBody UserAssemblyDTO request) {
        userAssembliesService.createUserAssemblies(request);
        return new ResponseEntity<>("Assembly add", HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUserAssembly(@RequestBody UserAssemblyDTO request,@PathVariable Long id) {
        userAssembliesService.updateUserAssemblies(request,id);
        return new ResponseEntity<>("Assembly by id " + id + " update", HttpStatus.OK);
    }
}
