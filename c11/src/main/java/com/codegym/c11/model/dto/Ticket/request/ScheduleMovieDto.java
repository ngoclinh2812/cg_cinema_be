package com.codegym.c11.model.dto.Ticket.request;

import com.codegym.c11.model.dto.request.MovieRequestDto;
import com.codegym.c11.model.dto.request.RoomRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduleMovieDto implements Serializable {
    private Long id;
    private MovieRequestDto movie;
    private RoomRequestDto room;
    private ScheduleDto schedule;

}
