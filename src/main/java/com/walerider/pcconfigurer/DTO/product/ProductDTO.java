package com.walerider.pcconfigurer.DTO.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private List<Integer> prices;
    private String category;
    private List<ProductAttributeDTO> productAttributes;
    private List<ProductImageDTO> productImages;

}
