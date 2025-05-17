package com.walerider.pcconfigurer.Controllers;

import com.walerider.pcconfigurer.DTO.product.CreateProductRequest;
import com.walerider.pcconfigurer.DTO.product.ProductDTO;
import com.walerider.pcconfigurer.entities.Product;
import com.walerider.pcconfigurer.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductService productService;

    @GetMapping("/all")
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/category/{id}")
    public List<ProductDTO> getAllProductsByCategoryId(@PathVariable Long id) {
        return productService.findByCategoryId(id);
    }

    @PostMapping("/create")
    public String create(@RequestBody CreateProductRequest request) {
        productService.create(request);
        return "product created";
    }

    /*
    * надо пофиксить DTO, чтобы у меня ещё аттрибуты со значениме выдавались, которые к продукту привязаны
    **/
}
