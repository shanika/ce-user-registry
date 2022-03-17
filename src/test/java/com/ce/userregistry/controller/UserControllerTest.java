package com.ce.userregistry.controller;

import com.ce.userregistry.transfomers.UserTransformer;
import com.ce.userregistry.dto.UserDto;
import com.ce.userregistry.entity.User;
import com.ce.userregistry.service.UserService;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@MicronautTest
class UserControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Inject
    private UserService userService;

    @Inject
    private UserTransformer userTransformer;

    @MockBean(UserTransformer.class)
    public UserTransformer mockUserTransformer() {
        return Mockito.mock(UserTransformer.class);
    }

    @MockBean(UserService.class)
    public UserService mockUserService() {
        return Mockito.mock(UserService.class);
    }

    @Test
    void testICanPostAValidUser() {

        UserDto user = new UserDto();
        User userEntity = new User();

        when(userTransformer.toDomain(user)).thenReturn(userEntity);

        HttpResponse<Object> response = client.toBlocking().exchange(HttpRequest.POST("/users", user));

        verify(userService).addUser(userEntity);
        assertEquals(HttpStatus.CREATED,response.getStatus());
    }

    @Test
    void testICanSearchUsersByLastName() {

        List<User> users = List.of(mock(User.class));
        when(userService.findUsersByLastName("Something")).thenReturn(users);
        when(userTransformer.toDtoList(users)).thenReturn(List.of(new UserDto()));

        HttpResponse<List<UserDto>> response = client.toBlocking().exchange(HttpRequest.GET("/users?lastName=Something")
                , Argument.listOf(UserDto.class));

        verify(userService).findUsersByLastName("Something");
        assertNotNull(response.body());
        assertEquals(response.body().size(), 1);

    }
}
