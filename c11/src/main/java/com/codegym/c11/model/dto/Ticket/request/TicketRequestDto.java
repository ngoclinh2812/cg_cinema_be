package com.codegym.c11.model.dto.Ticket.request;

import com.codegym.c11.model.dto.Ticket.request.ScheduleMovieDto;
import com.codegym.c11.model.dto.Ticket.request.SeatDto;
import com.codegym.c11.model.dto.Ticket.request.TicketAccountDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketRequestDto implements Serializable {

    private Long id;

    private TicketAccountDto account;

    private ScheduleMovieDto scheduleMovie;

    private SeatDto seat;
}
