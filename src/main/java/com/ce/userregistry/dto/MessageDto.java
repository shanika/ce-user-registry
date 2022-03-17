package com.ce.userregistry.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MessageDto {
    private final Integer code;
    private final String content;
}
