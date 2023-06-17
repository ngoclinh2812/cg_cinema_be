package com.codegym.c11.model.dto.response;

import com.codegym.c11.model.entity.Seat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public interface ITheaterDto {
    Long getTheater_id();
    String getTheater_name();
    String getMovie_name();
    String getShow_time();
    String getShow_date();
    String getRoom_name();
    Long getRoom_id();

}
