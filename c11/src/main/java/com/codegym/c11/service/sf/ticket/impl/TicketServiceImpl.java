package com.codegym.c11.service.sf.ticket.impl;

import com.codegym.c11.enums.ESeatStatus;
import com.codegym.c11.model.dto.Ticket.request.TicketRequestDto;
import com.codegym.c11.model.entity.Account;
import com.codegym.c11.model.entity.ScheduleMovie;
import com.codegym.c11.model.entity.Seat;
import com.codegym.c11.model.entity.Ticket;
import com.codegym.c11.repository.TicketRepository;
import com.codegym.c11.service.sf.IAccountService;
import com.codegym.c11.service.sf.scheduleMovie.IScheduleMovieService;
import com.codegym.c11.service.sf.seat.ISeatService;
import com.codegym.c11.service.sf.ticket.TicketService;
import com.codegym.c11.utils.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IScheduleMovieService iScheduleMovie;

    @Autowired
    private ISeatService seatService;

    @Override
    @Transactional
    public Ticket save(TicketRequestDto ticketDto) {

        Ticket ticket = new Ticket();

        Account account = accountService.findById(ticketDto.getAccount().getId());
        ScheduleMovie scheduleMovie = iScheduleMovie.getScheduleMovieByMovieRoomSchedule(
                                            ticketDto.getScheduleMovie().getMovie().getId(),
                                            ticketDto.getScheduleMovie().getSchedule().getId(),
                                            ticketDto.getScheduleMovie().getRoom().getId());
        Seat seat = seatService.getSeatById(ticketDto.getSeat().getId());

        if ((account != null) && (scheduleMovie != null) && (seat != null)) {
            ticket.setId(ticketDto.getId());
            ticket.setScheduleMovie(scheduleMovie);
            ticket.setAccount(account);
            seat.setStatus(ESeatStatus.FULL);
            ticket.setSeat(seat);
            ticketRepository.save(ticket);
        }
        return ticketRepository.findById(ticket.getId()).orElse(null);
    }

    public Ticket getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new NoSuchElementException("Ticket not found with ID: " + ticketId));
    }
}
