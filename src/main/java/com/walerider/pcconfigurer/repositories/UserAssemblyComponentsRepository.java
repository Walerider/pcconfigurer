package com.walerider.pcconfigurer.repositories;

import com.walerider.pcconfigurer.entities.UserAssemblyComponents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAssemblyComponentsRepository extends JpaRepository<UserAssemblyComponents, Long> {
    List<UserAssemblyComponents> findByAssemblyId(Long assemblyId);
}
