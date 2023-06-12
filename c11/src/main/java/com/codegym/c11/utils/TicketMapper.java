package com.codegym.c11.utils;

import com.codegym.c11.model.dto.request.Ticket.ScheduleMovieDto;
import com.codegym.c11.model.dto.request.Ticket.SeatDto;
import com.codegym.c11.model.dto.request.Ticket.TicketRequestDto;
import com.codegym.c11.model.entity.*;
import com.codegym.c11.service.sf.scheduleMovie.IScheduleMovieService;
import com.codegym.c11.service.sf.IAccountService;
import com.codegym.c11.service.sf.seat.ISeatService;
import com.codegym.c11.service.sf.seat.impl.SeatServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        Account account = accountService.findByUsername(ticketDto.getAccount().getUsername());
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
