package com.walerider.pcconfigurer.DTO.userAssembly;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAssemblyComponentsDto {
    private Long id;
    private String productName;
    private String componentCategory;
}
