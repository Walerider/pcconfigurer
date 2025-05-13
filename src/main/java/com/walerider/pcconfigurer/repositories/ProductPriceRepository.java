package com.walerider.pcconfigurer.repositories;

import com.walerider.pcconfigurer.entities.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {

    ProductPrice findByProductId(Long productId);
}
