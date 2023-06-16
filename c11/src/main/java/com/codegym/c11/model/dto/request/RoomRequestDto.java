package com.codegym.c11.model.dto.request;

import com.codegym.c11.model.entity.ScheduleMovie;
import com.codegym.c11.model.entity.Seat;
import com.codegym.c11.model.entity.Theater;
import lombok.Data;

import java.util.List;

@Data
public class RoomRequestDto {
    private Long id;
    private String name;
    private Theater TheaterId;
    private List<Seat> seatList;
    private List<ScheduleMovie> scheduleMovieList;
}
