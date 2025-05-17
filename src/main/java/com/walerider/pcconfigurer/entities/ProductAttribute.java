package com.walerider.pcconfigurer.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product_attributes")
@Builder
public class ProductAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @Setter
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @Setter
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;

    @ManyToOne(fetch = FetchType.LAZY)
    @Setter
    @JoinColumn(name = "attribute_value_id")
    private AttributeValue attributeValue;

    public ProductAttribute(Product product, Attribute attribute, AttributeValue attributeValue) {
        this.product = product;
        this.attribute = attribute;
        this.attributeValue = attributeValue;
    }
}
