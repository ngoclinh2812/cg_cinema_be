package com.codegym.c11.model.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public interface ITheaterDto {
    Long getTheaterId();
    String getTheaterName();
    String getMovieName();
    LocalDateTime getShowTime();
    LocalDateTime getShowDate();
    String getRoomName();
}
