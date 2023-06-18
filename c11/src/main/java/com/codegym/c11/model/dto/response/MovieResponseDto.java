package com.codegym.c11.model.dto.response;

import com.codegym.c11.model.entity.Status;
import lombok.Data;

import java.util.Date;

@Data
public class MovieResponseDto {
    private Long id;

    private String name;

    private String duration;

    private String trailer;

    private String img;

    private Integer rating;

    private Date dateStart;

    private Date dateEnd;

}
