package com.codegym.c11.utils;

import com.codegym.c11.model.dto.request.TheaterRequestDto;
import com.codegym.c11.model.dto.request.Ticket.ScheduleMovieDto;
import com.codegym.c11.model.dto.request.Ticket.SeatDto;
import com.codegym.c11.model.dto.request.Ticket.TicketRequestDto;
import com.codegym.c11.model.dto.response.TheaterResponseDto;
import com.codegym.c11.model.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    @Autowired
    private AccountMapper accountMapper;

    public Ticket convertToTicketEntity(TicketRequestDto ticketDto){
        Ticket ticket = new Ticket();
        ScheduleMovie scheduleMovie = convertToScheduleMovieEntity(ticketDto.getScheduleMovie());
        Account account = accountMapper.convertFromRequestDtoToEntity(ticketDto.getAccount());
        Seat seat = convertToSeatEntity(ticketDto.getSeat());

        ticket.setScheduleMovie(scheduleMovie);
        ticket.setAccount(account);
        ticket.setSeat(seat);
        return ticket;
    }

    private Seat convertToSeatEntity(SeatDto seatDto) {
        Seat seatEntity = new Seat();
        BeanUtils.copyProperties(seatDto, seatEntity);
        return seatEntity;
    }

    private ScheduleMovie convertToScheduleMovieEntity(ScheduleMovieDto scheduleMovieDto) {
        ScheduleMovie scheduleMovie = new ScheduleMovie();
        BeanUtils.copyProperties(scheduleMovieDto, scheduleMovie);
        return scheduleMovie;
    }

}
