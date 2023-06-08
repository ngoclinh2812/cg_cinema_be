package com.codegym.c11.model.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public interface ITheaterDto {
    Long getTheaterId();
    String getTheaterName();
    String getMovieName();
    String getScheduleShow_time();
    String getScheduleShow_date();
    String getRoomName();
}
