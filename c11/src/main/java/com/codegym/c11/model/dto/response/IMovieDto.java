package com.codegym.c11.model.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public interface IMovieDto {
    Long getMovieId();

    String getTheaterName();

    String getScheduleShow_time();

    String getScheduleShow_date();

    String getRoomName();
}
