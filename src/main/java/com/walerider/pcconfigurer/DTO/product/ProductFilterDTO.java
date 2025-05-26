package com.walerider.pcconfigurer.DTO.product;

import lombok.*;

import java.util.*;

@Data
@Builder
@AllArgsConstructor
public class ProductFilterDTO {

    private List<ProductAttributeDTO> productAttributes;
}
