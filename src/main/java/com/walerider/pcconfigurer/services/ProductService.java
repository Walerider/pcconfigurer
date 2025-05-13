package com.walerider.pcconfigurer.services;

import com.walerider.pcconfigurer.DTO.ProductDTO;
import com.walerider.pcconfigurer.entities.Product;
import com.walerider.pcconfigurer.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public List<ProductDTO> findByCategoryId(@PathVariable Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
}
