package com.codegym.c11.model.dto.response;

import com.codegym.c11.model.entity.Seat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> eb646a8b304d123be5c917499743ce909760ade1

@JsonIgnoreProperties(ignoreUnknown = true)
public interface ITheaterDto {
    Long getTheater_id();
    String getTheater_name();
    String getMovie_name();
    String getShow_time();
    String getShow_date();
    String getRoom_name();

}
