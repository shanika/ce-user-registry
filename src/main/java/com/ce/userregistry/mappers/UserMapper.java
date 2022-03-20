package com.ce.userregistry.mappers;

import com.ce.userregistry.entity.User;
import com.ce.userregistry.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        componentModel = "jsr330"
)
public abstract class UserMapper {

    @Mapping(target = "id", ignore = true)
    public abstract User toDomain(UserDto userDto);

    public abstract List<UserDto> toDtoList(List<User> users);
}
