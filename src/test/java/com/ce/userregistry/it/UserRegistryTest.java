package com.ce.userregistry.it;

import com.ce.userregistry.dto.UserDto;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class UserRegistryTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void ICanSearchUsersBySurname() {

        UserDto user1 = new UserDto();
        user1.setFirstName("firstname1");
        user1.setSurname("surname1");
        user1.setEmail("email1");

        client.toBlocking().exchange(HttpRequest.POST("/users", user1));

        UserDto user2 = new UserDto();
        user2.setFirstName("firstname2");
        user2.setSurname("surname1");
        user2.setEmail("email2");

        client.toBlocking().exchange(HttpRequest.POST("/users", user2));

        UserDto user3 = new UserDto();
        user3.setFirstName("firstname3");
        user3.setSurname("surname3");
        user3.setEmail("email3");

        client.toBlocking().exchange(HttpRequest.POST("/users", user3));

        HttpResponse<List<UserDto>> response = client.toBlocking().exchange(HttpRequest.GET("/users?surname=surname1")
                , Argument.listOf(UserDto.class));

        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());
        List<UserDto> result = response.getBody().get();

        assertEquals(result.size(), 2);

        assertEquals("surname1", result.get(0).getSurname());
        assertEquals("surname1", result.get(1).getSurname());

        assertEquals("firstname1", result.get(0).getFirstName());
        assertEquals("firstname2", result.get(1).getFirstName());

    }
}
