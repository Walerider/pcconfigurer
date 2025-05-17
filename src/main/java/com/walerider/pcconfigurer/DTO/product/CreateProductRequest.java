package com.walerider.pcconfigurer.DTO.product;

import lombok.Data;

@Data
public class CreateProductRequest {
    private Long categoryId;
    private String name;
    private String description;

    private Long attributeId;
    private Long attributeValueId;

    private int price;
    private String sourcePrice;
}
