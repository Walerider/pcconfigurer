package com.walerider.pcconfigurer.Controllers;

import com.walerider.pcconfigurer.entities.Product;
import com.walerider.pcconfigurer.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    private final ProductService productService;
    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("/category/{id}")
    public List<Product> getAllProductsByCategoryId(@PathVariable Long id) {
        return productService.findByCategoryId(id);
    }
}
