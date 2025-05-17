package com.walerider.pcconfigurer.Controllers;

import com.walerider.pcconfigurer.DTO.userAssembly.UserAssemblyRequest;
import com.walerider.pcconfigurer.DTO.userAssembly.UserAssemblyResponse;
import com.walerider.pcconfigurer.entities.UserAssembly;
import com.walerider.pcconfigurer.services.UserAssembliesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assemblies")
@RequiredArgsConstructor
public class UserAssembliesRestController {
    private final UserAssembliesService userAssembliesService;

    @GetMapping("/all")
    public List<UserAssemblyResponse> getAllUserAssemblies() {
        return userAssembliesService.getAllUserAssemblies();
    }

    @GetMapping("/user/{id}")
    public List<UserAssemblyResponse> getAllUserAssembliesDTOById(@PathVariable Long id) {
        return userAssembliesService.getAllUserAssembliesDTOByUserId(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUserAssembly(@RequestBody UserAssemblyRequest request) {
        userAssembliesService.createUserAssemblies(request);
        return new ResponseEntity<>("Assembly add", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUserAssembly(@RequestBody UserAssemblyRequest request, @PathVariable Long id) {
        userAssembliesService.updateUserAssemblies(request,id);
        return new ResponseEntity<>("Assembly by id " + id + " update", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserAssembly(@PathVariable Long id) {
        userAssembliesService.deleteUserAssembly(id);
        return new ResponseEntity<>("Assembly by id " + id + " delete", HttpStatus.OK);
    }

    /**
     * реализовать создание компонентов
     */

    /*сделать удаление сборок
    *  Надо DTO здешний пофиксить, чтобы мне сборки отдавало сразу с компонентами*/
}
