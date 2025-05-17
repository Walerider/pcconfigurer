package com.walerider.pcconfigurer.Mappers;

import com.walerider.pcconfigurer.DTO.userAssembly.UserAssemblyRequest;
import com.walerider.pcconfigurer.entities.UserAssembly;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserAssemblyMapper {
//    @Mapping(target = "id", source = "id")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "name", source = "name")
    UserAssemblyRequest toDto(UserAssembly userAssembly);
    List<UserAssemblyRequest> toDtoList(List<UserAssembly> assemblies);
}
