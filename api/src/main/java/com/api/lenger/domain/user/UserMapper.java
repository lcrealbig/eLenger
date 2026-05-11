package com.api.lenger.domain.user;

import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserDto toDto (User user);
}
