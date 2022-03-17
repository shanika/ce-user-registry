package com.ce.userregistry.transfomers;

import com.ce.userregistry.dto.UserDto;
import com.ce.userregistry.entity.User;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class UserTransformer {
    public User toDomain(UserDto user) {
        return null;
    }

    public List<UserDto> toDtoList(List<User> usersByLastName) {
        return null;
    }
}
