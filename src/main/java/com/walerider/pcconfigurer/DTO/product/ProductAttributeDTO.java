package com.walerider.pcconfigurer.DTO.product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductAttributeDTO {
    private String name;
    private String value;
}
