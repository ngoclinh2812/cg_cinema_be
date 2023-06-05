package com.codegym.c11.model.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TheaterRequestDto {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String img;
}
