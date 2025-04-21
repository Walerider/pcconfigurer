package com.walerider.pcconfigurer.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductAttribute {
    @Id
    @Getter
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @Getter
    @Setter
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter
    @Setter
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter
    @Setter
    @JoinColumn(name = "attribute_value_id")
    private AttributeValue attributeValue;

    public ProductAttribute(Product product, Attribute attribute, AttributeValue attributeValue) {
        this.product = product;
        this.attribute = attribute;
        this.attributeValue = attributeValue;
    }
}
