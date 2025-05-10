package com.walerider.pcconfigurer.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Categories {

    @Id
    @Getter
    @Column(name = "id", nullable = false)
    private Long id;

    @Getter
    @Setter
    @Column(name = "name", length = Integer.MAX_VALUE)
    private String name;

    @Getter
    @Setter
    @OneToMany(mappedBy = "category")
    private Set<Product> products = new LinkedHashSet<>();

    public Categories(String name) {
        this.name = name;
    }
}
