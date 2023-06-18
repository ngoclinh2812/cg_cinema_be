package com.codegym.c11.model.dto.Ticket.request;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ScheduleDto {

    private Long id;
    private LocalDateTime showTime;
    private LocalDateTime showDate;

}
