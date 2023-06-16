package com.codegym.c11.utils;

import com.codegym.c11.model.dto.Ticket.TicketResponseDto;
import com.codegym.c11.model.dto.Ticket.request.ScheduleMovieDto;
import com.codegym.c11.model.dto.Ticket.request.SeatDto;
import com.codegym.c11.model.dto.Ticket.request.TicketRequestDto;
import com.codegym.c11.model.dto.response.TheaterResponseDto;
import com.codegym.c11.model.entity.*;
import com.codegym.c11.service.sf.scheduleMovie.IScheduleMovieService;
import com.codegym.c11.service.sf.IAccountService;
import com.codegym.c11.service.sf.seat.ISeatService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TicketMapper {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IScheduleMovieService iScheduleMovie;

    @Autowired
    private ISeatService seatService;

    public Ticket convertToTicketEntity(TicketRequestDto ticketDto){
        Ticket ticket = new Ticket();

        ScheduleMovie scheduleMovie = iScheduleMovie.getScheduleMovieById(ticketDto.getScheduleMovie().getId());
        Account account = accountService.findById(ticketDto.getAccount().getId());
        Seat seat = seatService.getSeatById(ticketDto.getSeat().getId());

        if (account != null && account != null && seat != null) {
            ticket.setId(ticketDto.getId());
            ticket.setScheduleMovie(scheduleMovie);
            ticket.setAccount(account);
            ticket.setSeat(seat);
            return ticket;
        }
        return null;
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
