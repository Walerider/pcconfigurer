package com.walerider.pcconfigurer.DTO.category;

import com.walerider.pcconfigurer.DTO.product.ProductDTO;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CategoryResponse {
    private Long id;
    private String name;
}
