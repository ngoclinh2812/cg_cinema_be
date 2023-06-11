package com.codegym.c11.model.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public interface ITheaterDto {
    Long getTheater_id();
    String getTheater_name();
    String getMovie_name();
    String getShow_time();
    String getShow_date();
    String getRoom_name();
}
