package com.ce.userregistry.dto;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Introspected
public class UserDto {

    private String firstName;

    @NotEmpty
    private String lastName;

    private String email;
}
