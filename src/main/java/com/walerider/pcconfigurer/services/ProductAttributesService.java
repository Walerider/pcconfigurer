package com.walerider.pcconfigurer.services;

import com.walerider.pcconfigurer.entities.ProductAttribute;
import com.walerider.pcconfigurer.repositories.ProductAttributeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductAttributesService {
    private final ProductAttributeRepository productAttributeRepository;
    @Autowired
    public ProductAttributesService(ProductAttributeRepository productAttributeRepository) {
        this.productAttributeRepository = productAttributeRepository;

    }
}
