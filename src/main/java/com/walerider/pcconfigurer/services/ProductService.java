package com.walerider.pcconfigurer.services;

import com.walerider.pcconfigurer.DTO.product.CreateProductRequest;
import com.walerider.pcconfigurer.DTO.product.ProductDTO;
import com.walerider.pcconfigurer.entities.*;
import com.walerider.pcconfigurer.repositories.*;
import com.walerider.pcconfigurer.validation.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductPriceRepository productPriceRepository;
    private final CategoryRepository categoryRepository;
    private final AttributeRepository attributeRepository;
    private final AttributeValuesRepository attributeValuesRepository;
    private final ProductAttributeRepository productAttributeRepository;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAllWithSource();
    }

    public List<ProductDTO> findByCategoryId(@PathVariable Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public void create(CreateProductRequest request) {
        if (productRepository.existsByName(request.getName())) {
            throw new BadRequestException("product name must by unique");
        }

        Attribute attribute = attributeRepository.findById(request.getAttributeId())
                .orElseThrow(() -> new BadRequestException("attribute id not found"));
        AttributeValue attributeValue = attributeValuesRepository.findById(request.getAttributeValueId())
                .orElseThrow(() -> new BadRequestException("attribute value id not found"));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new BadRequestException("category id not found"));

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(category).build();
        productRepository.save(product);

        productPriceRepository.save(ProductPrice.builder()
                .price(request.getPrice())
                .product(product)
                .source(request.getSourcePrice()).build());

        productAttributeRepository.save(ProductAttribute.builder()
                .product(product)
                .attributeValue(attributeValue)
                .attribute(attribute).build());
    }
}
