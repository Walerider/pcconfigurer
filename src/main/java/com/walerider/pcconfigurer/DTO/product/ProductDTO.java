package com.walerider.pcconfigurer.DTO.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;

    private String categoryName;

    private Integer price;

    private String attributeName;
    private String attributeValue;
    //TODO сделать OneToMany отношение aттрибутов
}
