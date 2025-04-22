package com.walerider.pcconfigurer.services;

import com.walerider.pcconfigurer.repositories.ProductPricesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductPricesService {
    private final ProductPricesRepository productPricesRepository;
    @Autowired
    public ProductPricesService(ProductPricesRepository productPricesRepository) {
        this.productPricesRepository = productPricesRepository;
    }
}
