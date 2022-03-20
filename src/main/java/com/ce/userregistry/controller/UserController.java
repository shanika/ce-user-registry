package com.ce.userregistry.controller;

import com.ce.userregistry.dto.MessageDto;
import com.ce.userregistry.dto.UserDto;
import com.ce.userregistry.service.UserService;
import com.ce.userregistry.mappers.UserMapper;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Validated
@Controller("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Post
    public HttpResponse<?> addUser(@Valid @Body UserDto user) {
        userService.addUser(userMapper.toDomain(user));
        return HttpResponse.status(HttpStatus.CREATED).body(new MessageDto(HttpStatus.CREATED.getCode(),"Saved successfully !"));
    }

    @Get
    public List<UserDto> findUsers(@QueryValue String lastName) {
        return userMapper.toDtoList(userService.findUsersByLastName(lastName));
    }

}
