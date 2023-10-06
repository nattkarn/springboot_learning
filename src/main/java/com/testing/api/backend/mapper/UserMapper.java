package com.testing.api.backend.mapper;


import com.testing.api.backend.entity.User;
import com.testing.api.backend.model.RegisterResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    RegisterResponse toRegisterResponse(User user);
}
