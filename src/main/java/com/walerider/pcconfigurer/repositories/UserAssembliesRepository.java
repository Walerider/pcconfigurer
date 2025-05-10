package com.walerider.pcconfigurer.repositories;

import com.walerider.pcconfigurer.entities.UserAssemblies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAssembliesRepository extends JpaRepository<UserAssemblies, Long> {

}
