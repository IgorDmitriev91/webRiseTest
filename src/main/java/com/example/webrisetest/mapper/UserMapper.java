package com.example.webrisetest.mapper;


import com.example.webrisetest.config.MapperConfiguration;
import com.example.webrisetest.dto.UserRequestDTO;
import com.example.webrisetest.dto.UserResponseDTO;
import com.example.webrisetest.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfiguration.class)
public interface UserMapper {

    UserResponseDTO toDTO(UserModel userModel);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subscriptions", ignore = true)
    UserModel toEntity(UserRequestDTO userRequestDTO);
}
