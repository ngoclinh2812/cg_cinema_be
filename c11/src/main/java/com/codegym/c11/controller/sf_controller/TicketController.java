package com.codegym.c11.controller.sf_controller;

import com.codegym.c11.model.dto.Ticket.request.TicketRequestDto;
import com.codegym.c11.model.entity.Ticket;
import com.codegym.c11.service.sf.IAccountService;
import com.codegym.c11.service.sf.email.EmailService;
import com.codegym.c11.service.sf.ticket.TicketService;
import com.codegym.c11.utils.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private EmailService emailService;

    @PostMapping()
    public ResponseEntity<?> createTicket(@RequestBody TicketRequestDto ticketDto) {
        Ticket savedTicket = ticketService.save(ticketDto);
        emailService.sendTicketConfirmedEmail(savedTicket);
        return ResponseEntity.ok(savedTicket);
    }

    @GetMapping
    public String test() {
        return "Hello";
    }
}
