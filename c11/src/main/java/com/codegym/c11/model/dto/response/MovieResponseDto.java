package com.codegym.c11.model.dto.response;

import lombok.Data;

@Data
public class MovieResponseDto {
    private Long id;
    private String name;
    private String decription;
    private String trailer;
    private String img;
}
