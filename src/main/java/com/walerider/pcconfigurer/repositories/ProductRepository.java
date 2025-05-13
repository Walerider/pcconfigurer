package com.walerider.pcconfigurer.repositories;

import com.walerider.pcconfigurer.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findByCategoryId(Long id);
}
