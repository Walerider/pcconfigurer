package com.walerider.pcconfigurer.repositories;

import com.walerider.pcconfigurer.entities.ProductPrices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPricesRepository extends JpaRepository<ProductPrices, Long> {

}
