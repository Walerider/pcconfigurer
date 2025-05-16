package com.walerider.pcconfigurer.Controllers;

import com.walerider.pcconfigurer.DTO.ProductDTO;
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

    @GetMapping("/all")//todo тут тоже DTO должен быть
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("/category/{id}")
    public List<ProductDTO> getAllProductsByCategoryId(@PathVariable Long id) {
        return productService.findByCategoryId(id);
    }
    /*todo надо пофиксить DTO, чтобы у меня ещё аттрибуты со значениме выдавались, которые к продукту привязаны
    *  */
}
