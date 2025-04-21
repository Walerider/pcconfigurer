package com.walerider.pcconfigurer.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AttributeValue {
    @Id
    @Getter
    @Column(name = "id", nullable = false)
    private Integer id;


    @Getter
    @Setter
    @Column(name = "value", length = Integer.MAX_VALUE)
    private String value;

    public AttributeValue(String value) {
        this.value = value;
    }
}
