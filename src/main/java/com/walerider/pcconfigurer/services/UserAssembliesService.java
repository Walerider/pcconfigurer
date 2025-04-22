package com.walerider.pcconfigurer.services;

import com.walerider.pcconfigurer.repositories.UserAssembliesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserAssembliesService {
    private final UserAssembliesRepository userAssembliesRepository;
    @Autowired
    public UserAssembliesService(UserAssembliesRepository userAssembliesRepository) {
        this.userAssembliesRepository = userAssembliesRepository;

    }
}
