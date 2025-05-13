package com.walerider.pcconfigurer.repositories;

import com.walerider.pcconfigurer.DTO.ProductDTO;
import com.walerider.pcconfigurer.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT new com.walerider.pcconfigurer.DTO.ProductDTO(p.id, p.name, p.description, pr.price) FROM Product p JOIN ProductPrice pr ON pr.product.id = p.id WHERE p.category.id = :id")
    public List<ProductDTO> findByCategoryId(Long id);
}
