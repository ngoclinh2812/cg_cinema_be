package com.codegym.c11.model.dto.request.Ticket;

import com.codegym.c11.model.dto.request.MovieRequestDto;
import com.codegym.c11.model.dto.request.RoomRequestDto;
import com.codegym.c11.model.entity.Schedule;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduleMovieDto {

    private Long id;


}
