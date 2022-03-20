package com.ce.userregistry.controller;

import com.ce.userregistry.dto.UserDto;
import com.ce.userregistry.entity.User;
import com.ce.userregistry.mappers.UserMapper;
import com.ce.userregistry.service.UserService;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
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
    private UserMapper userMapper;

    @MockBean(UserMapper.class)
    public UserMapper mockUserMapper() {
        return Mockito.mock(UserMapper.class);
    }

    @MockBean(UserService.class)
    public UserService mockUserService() {
        return Mockito.mock(UserService.class);
    }

    @Test
    void testICanCreateAValidUser() {

        UserDto user = new UserDto();
        user.setSurname("surname");

        User userEntity = new User();

        when(userMapper.toDomain(user)).thenReturn(userEntity);

        HttpResponse<Object> response = client.toBlocking().exchange(HttpRequest.POST("/users", user));

        verify(userService).addUser(userEntity);
        assertEquals(HttpStatus.CREATED,response.getStatus());
    }

    @Test
    void testICanNotCreateAUserWithoutLastname () {
        UserDto user = new UserDto();
        User userEntity = new User();

        when(userMapper.toDomain(user)).thenReturn(userEntity);

        HttpClientResponseException exception = assertThrows(HttpClientResponseException.class,
                () -> client.toBlocking().exchange(HttpRequest.POST("/users", user)));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    }

    @Test
    void testICanSearchUsersBySurname() {

        List<User> users = List.of(mock(User.class));
        when(userService.findUsersBySurname("Something")).thenReturn(users);
        UserDto userDto = new UserDto();
        when(userMapper.toDtoList(users)).thenReturn(List.of(userDto));

        HttpResponse<List<UserDto>> response = client.toBlocking().exchange(HttpRequest.GET("/users?surname=Something")
                , Argument.listOf(UserDto.class));

        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());

        List<UserDto> result = response.getBody().get();
        assertEquals(result.size(), 1);
        assertEquals(userDto, result.get(0));
    }

    @Test
    void testSearchByEmptySurnameReturnsZeroResults() {

        List<User> users = List.of();
        when(userService.findUsersBySurname("")).thenReturn(users);
        when(userMapper.toDtoList(users)).thenReturn(List.of());

        HttpResponse<List<UserDto>> response = client.toBlocking().exchange(HttpRequest.GET("/users?surname=")
                , Argument.listOf(UserDto.class));

        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());

        List<UserDto> result = response.getBody().get();
        assertEquals(result.size(), 0);
    }

    @Test
    void testSurnameParameterIsMandatoryForSearch() {
        HttpClientResponseException exception = assertThrows(HttpClientResponseException.class,
                () -> client.toBlocking().exchange(HttpRequest.GET("/users"), Argument.listOf(UserDto.class)));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    }
}
