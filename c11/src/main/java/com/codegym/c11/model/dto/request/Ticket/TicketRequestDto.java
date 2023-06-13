package com.codegym.c11.model.dto.request.Ticket;

import com.codegym.c11.model.dto.request.AccountRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketRequestDto {

    private Long id;

    private AccountRequestDto account;

    private ScheduleMovieDto scheduleMovie;

    private SeatDto seat;
}
