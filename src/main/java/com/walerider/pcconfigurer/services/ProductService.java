package com.walerider.pcconfigurer.services;

import com.walerider.pcconfigurer.DTO.product.CreateProductRequest;
import com.walerider.pcconfigurer.DTO.product.ProductAttributeDTO;
import com.walerider.pcconfigurer.DTO.product.ProductDTO;
import com.walerider.pcconfigurer.DTO.product.ProductFilterDTO;
import com.walerider.pcconfigurer.entities.*;
import com.walerider.pcconfigurer.repositories.*;
import com.walerider.pcconfigurer.validation.exceptions.BadRequestException;
import jakarta.persistence.criteria.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        return toProductDTOList(productRepository.findAllWithSource());
    }
    public List<ProductDTO> findByAttributes(@PathVariable Long id, @RequestBody @Valid ProductFilterDTO filterRequest) {
        if(filterRequest.getProductAttributes().isEmpty()) {
            return toProductDTOList(productRepository.findByCategoryId(id));
        }
        Specification<Product> spec = (root, query, cb) -> {
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Product> subRoot = subquery.from(Product.class);

            Join<Product, ProductAttribute> subAttributeJoin = subRoot.join("productAttributes");
            Join<Product, Category> subCategoryJoin = subRoot.join("category");
            Join<ProductAttribute, Attribute> subAttribute = subAttributeJoin.join("attribute");
            Join<ProductAttribute, AttributeValue> subAttributeValue = subAttributeJoin.join("attributeValue");


            Map<String, List<String>> attributesByName = filterRequest.getProductAttributes().stream()
                    .collect(Collectors.groupingBy(
                            ProductAttributeDTO::getName,
                            Collectors.mapping(ProductAttributeDTO::getValue, Collectors.toList())
                    ));

            List<Predicate> subPredicates = new ArrayList<>();
            attributesByName.forEach((attrName, values) -> {
                subPredicates.add(cb.and(
                        cb.equal(subAttribute.get("name"), attrName),
                        subAttributeValue.get("value").in(values)
                ));
            });
            Predicate categoryPredicate = id != null ?
                    cb.equal(subCategoryJoin.get("id"), id) :
                    cb.conjunction();
            subquery.select(subRoot.get("id"))
                    .where(cb.and(categoryPredicate,cb.or(subPredicates.toArray(new Predicate[0]))))
                    .groupBy(subRoot.get("id"))
                    .having(cb.equal(cb.countDistinct(subAttribute.get("name")),
                            (long) attributesByName.size()));

            return root.get("id").in(subquery);
        };

        List<Product> products = productRepository.findAll(spec);
        if(products.isEmpty()) {
            throw new BadRequestException("No products found");
        }
        return products.stream()
                .map(ProductService::toProductDTO)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> findByCategoryId(@PathVariable Long categoryId) {
        return toProductDTOList(productRepository.findByCategoryId(categoryId));
    }

    public ProductDTO findById(@PathVariable Long id) {
        return toProductDTO(productRepository.findById(id).orElse(null));
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

    private List<ProductDTO> toProductDTOList(List<Product> products) {
        return products.stream()
                .map(p -> ProductDTO.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .description(p.getDescription())
                        .prices(p.getProductPrices().stream()
                                .map(ProductPrice::getPrice).toList()
                        )
                        .productAttributes(p.getProductAttributes().stream()
                                .map(a -> ProductAttributeDTO.builder()
                                        .name(a.getAttribute().getName())
                                        .value(a.getAttributeValue().getValue()).build()
                                ).toList()
                        ).build())
                .toList();
    }
    private static ProductDTO toProductDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .prices(product.getProductPrices().stream()
                        .map(ProductPrice::getPrice).toList()
                )
                .productAttributes(product.getProductAttributes().stream()
                        .map(a -> ProductAttributeDTO.builder()
                                .name(a.getAttribute().getName())
                                .value(a.getAttributeValue().getValue()).build()
                        ).toList()
                ).build();
    }
}
