package com.walerider.pcconfigurer.services;

import com.walerider.pcconfigurer.entities.ProductPrice;
import com.walerider.pcconfigurer.repositories.ProductPriceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@Transactional
public class ProductPriceService {
        private final ProductPriceRepository productPriceRepository;

    public ProductPriceService(ProductPriceRepository productPriceRepository) {
        this.productPriceRepository = productPriceRepository;
    }
    public ProductPrice getProductPriceByProductId(@PathVariable long id) {
        if(productPriceRepository.findByProductId(id) == null){
            throw new RuntimeException("Product not found");
        }
        return productPriceRepository.findByProductId(id);
    }
}

