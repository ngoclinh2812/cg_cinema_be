package com.codegym.c11.controller.sf_controller;

import com.codegym.c11.model.dto.request.Ticket.TicketRequestDto;
import com.codegym.c11.model.entity.Ticket;
import com.codegym.c11.service.sf.ticket.TicketService;
import com.codegym.c11.utils.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private TicketService ticketService;

    @PostMapping()
    public ResponseEntity<?> createTicket(@RequestBody TicketRequestDto ticketDto) {
        Ticket ticketEntity = ticketMapper.convertToTicketEntity(ticketDto);
        Ticket savedTicket = ticketService.save(ticketEntity);
        return ResponseEntity.ok(savedTicket);
    }
}
