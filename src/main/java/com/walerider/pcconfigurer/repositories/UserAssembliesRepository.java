package com.walerider.pcconfigurer.repositories;

import com.walerider.pcconfigurer.entities.UserAssembly;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserAssembliesRepository extends JpaRepository<UserAssembly, Long> {
    UserAssembly findByName(String name);
    List<UserAssembly> findByUserId(long userId);

    @Query("""
        SELECT a FROM UserAssembly a
        LEFT JOIN FETCH a.userAssemblyComponents
        LEFT JOIN FETCH a.user
    """)
    List<UserAssembly> findAllWithComponents();
    @Query("""
        SELECT a FROM UserAssembly a
        LEFT JOIN FETCH a.userAssemblyComponents
        WHERE a.user.id = :userId
    """)
    List<UserAssembly> findAllByUserIdWithComponents(Long userId);
    @Query("""
        SELECT a FROM UserAssembly a
        LEFT JOIN FETCH a.userAssemblyComponents
        WHERE a.id = :id
    """)
    UserAssembly findByIdWithComponents(Long id);

}
