package com.walerider.pcconfigurer.services;

import com.walerider.pcconfigurer.DTO.userAssembly.UserAssemblyComponentsDto;
import com.walerider.pcconfigurer.DTO.userAssembly.UserAssemblyRequest;
import com.walerider.pcconfigurer.DTO.userAssembly.UserAssemblyResponse;
import com.walerider.pcconfigurer.entities.Product;
import com.walerider.pcconfigurer.entities.User;
import com.walerider.pcconfigurer.entities.UserAssembly;
import com.walerider.pcconfigurer.entities.UserAssemblyComponents;
import com.walerider.pcconfigurer.repositories.ProductRepository;
import com.walerider.pcconfigurer.repositories.UserAssembliesRepository;
import com.walerider.pcconfigurer.repositories.UserAssemblyComponentsRepository;
import com.walerider.pcconfigurer.repositories.UserRepository;
import com.walerider.pcconfigurer.validation.exceptions.BadRequestException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserAssembliesService {
    private final UserAssembliesRepository userAssembliesRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final UserAssemblyComponentsRepository userAssemblyComponentsRepository;
//    private final UserAssemblyMapper mapper;

    public List<UserAssemblyResponse> getAllUserAssemblies() {
        List<UserAssembly> userAssemblies = userAssembliesRepository.findAllWithComponents();
        return userAssemblies.stream()
                .map((a) -> UserAssemblyResponse.builder()
                        .id(a.getId())
                        .userId(a.getUser().getId())
                        .name(a.getName())
                        .userAssemblyComponents(a.getUserAssemblyComponents().stream()
                                .map(ac -> UserAssemblyComponentsDto.builder()
                                        .id(ac.getProduct().getId())
                                        .productName(ac.getProduct().getName())
                                        .componentCategory(ac.getProduct().getCategory().getName())
                                        .build())
                                .toList()
                        )
                        .price(a.getPrice())
                        .build())
                .toList();
    }

    public List<UserAssemblyResponse> getAllUserAssembliesDTOByUserId(Long userId) {
        List<UserAssembly> userAssemblies = userAssembliesRepository.findAllByUserIdWithComponents(userId);
        return userAssemblies.stream()
                .map((a) -> UserAssemblyResponse.builder()
                        .id(a.getId())
                        .userId(a.getUser().getId())
                        .name(a.getName())
                        .userAssemblyComponents(a.getUserAssemblyComponents().stream()
                                .map(ac -> UserAssemblyComponentsDto.builder()
                                        .id(ac.getProduct().getId())
                                        .productName(ac.getProduct().getName())
                                        .componentCategory(ac.getProduct().getCategory().getName())
                                        .build())
                                .toList()
                        )
                        .price(a.getPrice())
                        .build())
                .toList();
    }
    public UserAssemblyResponse getAllUserAssembliesDTOById(Long id) {
        UserAssembly userAssembly = userAssembliesRepository.findByIdWithComponents(id);
        return UserAssemblyResponse.builder()
                .id(userAssembly.getId())
                .userId(userAssembly.getUser().getId())
                .name(userAssembly.getName())
                .userAssemblyComponents(userAssembly.getUserAssemblyComponents().stream()
                        .map(ac -> UserAssemblyComponentsDto.builder()
                                .id(ac.getProduct().getId())
                                .productName(ac.getProduct().getName())
                                .componentCategory(ac.getProduct().getCategory().getName()).build())
                        .toList())
                .price(userAssembly.getPrice())
                .build();

    }

    public UserAssembly createUserAssemblies(UserAssemblyRequest request) {
        User user = userRepository
                .findById(request.getUserId())
                .orElseThrow(() -> new BadRequestException("User doesn't exist"));

        UserAssembly userAssembly = new UserAssembly(request.getName(), user,request.getPrice());
        if (userAssembliesRepository.findByName(request.getName()) != null && userAssembliesRepository.findByName(request.getName()).getUser().getId().equals(user.getId())) {
            throw new BadRequestException("Assembly with this name already exists");
        }
        if (productRepository.countByIdIn(request.getProductIds()) != request.getProductIds().size()) {
            throw new BadRequestException("not valid ids");
        }

        userAssembliesRepository.save(userAssembly);

        List<Product> products = productRepository.findAllById(request.getProductIds());
        List<UserAssemblyComponents> userAssemblyComponentsList = products.stream()
                .map(p -> UserAssemblyComponents.builder()
                        .assembly(userAssembly)
                        .product(p).build())
                .toList();
        userAssemblyComponentsRepository.saveAll(userAssemblyComponentsList);
        return userAssembly;
    }

    public void updateUserAssemblies(UserAssemblyRequest request, Long assemblyId) {
        userAssembliesRepository.findById(assemblyId)
                .map(assembly -> {
                    assembly.setName(request.getName());
                    assembly.setUser(userRepository.findById(request.getUserId())
                            .orElseThrow(() -> new BadRequestException("User doesnt exist")));
                    return userAssembliesRepository.save(assembly);
                }).orElseThrow(() -> new BadRequestException("Assembly not found"));
    }

    public void deleteUserAssembly(Long id) {
        if (!userAssembliesRepository.existsById(id)) {
            throw new BadRequestException("assembly not found");
        }
        userAssembliesRepository.deleteById(id);
    }
}
