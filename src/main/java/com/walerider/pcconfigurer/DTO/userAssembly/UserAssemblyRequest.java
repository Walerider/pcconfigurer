package com.walerider.pcconfigurer.DTO.userAssembly;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class UserAssemblyRequest {
    private String name;
    private Long userId;

    Set<Long> productIds;
}

