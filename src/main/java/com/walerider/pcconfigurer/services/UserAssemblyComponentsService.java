package com.walerider.pcconfigurer.services;

import com.walerider.pcconfigurer.repositories.UserAssemblyComponentsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserAssemblyComponentsService {
    private final UserAssemblyComponentsRepository userAssemblyComponentsRepository;
    @Autowired
    public UserAssemblyComponentsService(UserAssemblyComponentsRepository userAssemblyComponentsRepository) {
        this.userAssemblyComponentsRepository = userAssemblyComponentsRepository;
    }
    //todo сделать получение компонентов по id сборки
}
