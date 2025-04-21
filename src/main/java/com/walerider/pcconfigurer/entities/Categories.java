package com.walerider.pcconfigurer.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Categories {

    @Id
    @Getter
    @Column(name = "id", nullable = false)
    private Integer id;

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
