package com.walerider.pcconfigurer.repositories;

import com.walerider.pcconfigurer.entities.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeValuesRepository extends JpaRepository<AttributeValue, Long> {
    boolean existsByValue(String value);
}
