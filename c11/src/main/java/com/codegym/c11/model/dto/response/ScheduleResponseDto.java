package com.codegym.c11.model.dto.response;

import com.codegym.c11.model.dto.Ticket.request.ScheduleMovieDto;
import com.codegym.c11.model.entity.ScheduleMovie;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class ScheduleResponseDto {
    private Long id;
    private LocalDateTime showTime;
    private LocalDateTime showDate;
    private List<ScheduleMovieDto> scheduleMovieListDto;
}
