package com.walerider.pcconfigurer.repositories;

import com.walerider.pcconfigurer.entities.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Long> {
}
