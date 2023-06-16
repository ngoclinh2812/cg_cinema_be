package com.codegym.c11.model.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class MovieResponseDto {
    private Long id;
    private String name;
    private String description;
    private String trailer;
    private String img;
    private Date dateStart;
    private Date dateEnd;

}

