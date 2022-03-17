package com.ce.userregistry.controller;

import com.ce.userregistry.dto.MessageDto;
import com.ce.userregistry.dto.UserDto;
import com.ce.userregistry.service.UserService;
import com.ce.userregistry.transfomers.UserTransformer;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserTransformer userTransformer;

    @Post()
    public HttpResponse<?> addUser(@Body UserDto user) {
        userService.addUser(userTransformer.toDomain(user));
        return HttpResponse.status(HttpStatus.CREATED).body(new MessageDto(HttpStatus.CREATED.getCode(),"Saved successfully !"));
    }

    @Get
    public List<UserDto> findUsers(@Nullable @QueryValue String lastName) {
        return userTransformer.toDtoList(userService.findUsersByLastName(lastName));
    }

}
