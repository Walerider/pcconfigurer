package com.walerider.pcconfigurer.repositories;

import com.walerider.pcconfigurer.entities.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {

}
