package com.walerider.pcconfigurer.services;

import com.walerider.pcconfigurer.entities.UserAssemblyComponents;
import com.walerider.pcconfigurer.repositories.UserAssemblyComponentsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserAssemblyComponentsService {
    private final UserAssemblyComponentsRepository userAssemblyComponentsRepository;

    @Autowired
    public UserAssemblyComponentsService(UserAssemblyComponentsRepository userAssemblyComponentsRepository) {
        this.userAssemblyComponentsRepository = userAssemblyComponentsRepository;
    }

    public List<UserAssemblyComponents> getComponentsByAssemblyId(Long assemblyId) {
        return userAssemblyComponentsRepository.findByAssemblyId(assemblyId);
    }

    //сделать получение компонентов по id сборки
}
