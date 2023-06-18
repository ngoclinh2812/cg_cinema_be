package com.codegym.c11.model.dto.response;

import com.codegym.c11.model.entity.Status;
import lombok.Data;

import java.util.Date;

@Data
public class MovieResponseDto {
    private Long id;

    private String title;

    private String summary;

    private String description;

    private String duration;

    private String trailer;

    private String imageUrl;

    private Integer rating;

    private Date dateStart;

    private Date dateEnd;

    private Status status;
}
