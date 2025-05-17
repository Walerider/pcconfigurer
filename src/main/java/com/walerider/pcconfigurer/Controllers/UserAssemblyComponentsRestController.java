package com.walerider.pcconfigurer.Controllers;

import com.walerider.pcconfigurer.DTO.userAssembly.UserAssemblyComponentsDto;
import com.walerider.pcconfigurer.services.UserAssemblyComponentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/assemblies/components")
@RequiredArgsConstructor
public class UserAssemblyComponentsRestController {
    private final UserAssemblyComponentsService userAssemblyComponentsService;

    @GetMapping("/{id}")
    public List<UserAssemblyComponentsDto> getComponents(@PathVariable Long id) {
        return userAssemblyComponentsService
                .getComponentsByAssemblyId(id).stream()
                .map(ac -> UserAssemblyComponentsDto.builder()
                        .productName(ac.getProduct().getName())
                        .id(ac.getId()).build())
                .toList();
    }
}
