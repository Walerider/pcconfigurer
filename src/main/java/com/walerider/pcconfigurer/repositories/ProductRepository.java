package com.walerider.pcconfigurer.repositories;

import com.walerider.pcconfigurer.DTO.product.ProductDTO;
import com.walerider.pcconfigurer.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("""
                SELECT new com.walerider.pcconfigurer.DTO.product.ProductDTO
                (p.id, p.name, p.description, c.name, pr.price, pa.attribute.name, pa.attributeValue.value)
                FROM Product p
                LEFT JOIN ProductPrice pr ON pr.product.id = p.id
                LEFT JOIN Category c ON p.category.id = c.id
                LEFT JOIN ProductAttribute pa ON pa.product.id = p.id
                WHERE p.category.id = :id
            """)
    List<ProductDTO> findByCategoryId(Long id);
    //TODO сделать OneToMany отношение aттрибутов

    @Query("""
                SELECT new com.walerider.pcconfigurer.DTO.product.ProductDTO
                (p.id, p.name, p.description, c.name, pr.price, pa.attribute.name, pa.attributeValue.value)
                FROM Product p
                LEFT JOIN ProductPrice pr ON pr.product.id = p.id
                LEFT JOIN Category c ON p.category.id = c.id
                LEFT JOIN ProductAttribute pa ON pa.product.id = p.id
            """)
    List<ProductDTO> findAllWithSource();

    boolean existsByName(String name);

    @Query("SELECT COUNT(p) FROM Product p WHERE p.id IN :ids")
    long countByIdIn(Collection<Long> ids);
}
