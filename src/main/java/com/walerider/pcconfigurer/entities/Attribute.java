package com.walerider.pcconfigurer.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="attributes")
@Builder
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = Integer.MAX_VALUE, unique = true)
    @Setter
    private String name;

    public Attribute(String name) {
        this.name = name;
    }
}
