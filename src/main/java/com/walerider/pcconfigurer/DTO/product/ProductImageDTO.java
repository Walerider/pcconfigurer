package com.walerider.pcconfigurer.DTO.product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductImageDTO {
    private long id;
    private long productId;
    private String source;
}
