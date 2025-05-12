package com.walerider.pcconfigurer.DTO;


import lombok.*;
@Data
@Builder
@AllArgsConstructor
public class UserAssemblyDTO {
    private Long id;
    private String name;
    @NonNull
    private Long userId;


}

