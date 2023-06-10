package com.codegym.c11.service.sf.ticket.impl;

import com.codegym.c11.model.entity.Ticket;
import com.codegym.c11.repository.TicketRepository;
import com.codegym.c11.service.sf.ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.NoSuchElementException;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Ticket save(Ticket ticketEntity) {
        return ticketRepository.save(ticketEntity);
    }

    public Ticket getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new NoSuchElementException("Ticket not found with ID: " + ticketId));
    }
}
