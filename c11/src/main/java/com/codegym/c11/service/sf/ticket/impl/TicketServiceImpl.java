package com.codegym.c11.service.sf.ticket.impl;

import com.codegym.c11.model.dto.request.Ticket.TicketRequestDto;
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

import javax.persistence.EntityManager;
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
    public Ticket save(TicketRequestDto ticketDto) {

        Ticket ticket = new Ticket();

        ScheduleMovie scheduleMovie = iScheduleMovie.getScheduleMovieById(ticketDto.getScheduleMovie().getId());
        Account account = accountService.findByUsername(ticketDto.getAccount().getUsername());
        Seat seat = seatService.getSeatById(ticketDto.getSeat().getId());

        if (account != null && account != null && seat != null) {
            ticket.setId(ticketDto.getId());
            ticket.setScheduleMovie(scheduleMovie);
            ticket.setAccount(account);
            ticket.setSeat(seat);
            return ticketRepository.save(ticket);
        }
        return null;
    }

    public Ticket getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new NoSuchElementException("Ticket not found with ID: " + ticketId));
    }
}
