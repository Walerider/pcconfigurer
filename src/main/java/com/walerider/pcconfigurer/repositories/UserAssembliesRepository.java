package com.walerider.pcconfigurer.repositories;

import com.walerider.pcconfigurer.entities.UserAssembly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserAssembliesRepository extends JpaRepository<UserAssembly, Long> {
    public UserAssembly findByName(String name);
    public List<UserAssembly> findByUserId(long userId);
}
