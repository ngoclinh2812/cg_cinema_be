package com.codegym.c11.model.dto.Ticket.request;

import com.codegym.c11.model.dto.Ticket.request.ScheduleMovieDto;
import com.codegym.c11.model.dto.Ticket.request.SeatDto;
import com.codegym.c11.model.dto.Ticket.request.TicketAccountDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketRequestDto {

    private Long id;

    private TicketAccountDto account;

    private ScheduleMovieDto scheduleMovie;

    private SeatDto seat;
}
