package com.walerider.pcconfigurer.DTO.userAssembly;

import com.walerider.pcconfigurer.entities.UserAssemblyComponents;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class UserAssemblyResponse {
    private Long id;
    private String name;
    private Long userId;
    private int price;
    private List<UserAssemblyComponentsDto> userAssemblyComponents;
}
