package com.walerider.pcconfigurer.Controllers;

import com.walerider.pcconfigurer.entities.ProductPrice;
import com.walerider.pcconfigurer.services.ProductPriceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products/price")
public class ProductPriceRestController {
    private final ProductPriceService productPriceService;
    public ProductPriceRestController(ProductPriceService productPriceService) {
        this.productPriceService = productPriceService;
    }
    @GetMapping("/{id}")
    public ProductPrice getProductPrice(@PathVariable long id) {
        return productPriceService.getProductPriceByProductId(id);
    }
}
