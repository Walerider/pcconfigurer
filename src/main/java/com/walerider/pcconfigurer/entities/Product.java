package com.walerider.pcconfigurer.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

    @Column(name="name", length = Integer.MAX_VALUE, unique = true)
    private String name;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductAttribute> productAttributes;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductPrice> productPrices;

    public Product(Category category, String name, String description) {
        this.category = category;
        this.name = name;
        this.description = description;
    }
}