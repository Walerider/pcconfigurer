package com.walerider.pcconfigurer.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ProductPrices {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "price")
    private Integer price;

    @Column(name = "source", length = Integer.MAX_VALUE)
    private String source;

    public ProductPrices(Product product, Integer price, String source) {
        this.product = product;
        this.price = price;
        this.source = source;
    }
}
