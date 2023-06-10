package com.codegym.c11.model.dto.request.Ticket;

import lombok.Data;


@Data
public class ScheduleDto {

    private Long id;
    private String showTime;
    private String showDate;
}
