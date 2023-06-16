package com.codegym.c11.model.dto.response;

import com.codegym.c11.model.dto.Ticket.request.ScheduleMovieDto;
import com.codegym.c11.model.entity.ScheduleMovie;
import com.codegym.c11.model.entity.Seat;
import com.codegym.c11.model.entity.Theater;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomResponseDto {
    private Long id;
    private String name;
    private Theater TheaterId;
    private List<SeatResponseDto> seatList;
    private List<ScheduleMovieDto> scheduleMovieList;
}
