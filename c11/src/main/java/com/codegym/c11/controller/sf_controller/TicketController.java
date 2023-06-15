package com.codegym.c11.controller.sf_controller;

import com.codegym.c11.exception.GlobalExceptionHandler;
import com.codegym.c11.exception.api.EmailSendingException;
import com.codegym.c11.exception.api.ResourceNotFoundException;
import com.codegym.c11.model.dto.Ticket.request.TicketRequestDto;
import com.codegym.c11.model.entity.Ticket;
import com.codegym.c11.service.sf.IAccountService;
import com.codegym.c11.service.sf.email.EmailService;
import com.codegym.c11.service.sf.ticket.TicketService;
import com.codegym.c11.utils.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticket")
@CrossOrigin("http://localhost:3000")
public class TicketController {

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private EmailService emailService;

    @PostMapping()
    public ResponseEntity<?> createTicket(@RequestBody TicketRequestDto ticketDto) {
        try {
            Ticket savedTicket = ticketService.save(ticketDto);
            if (savedTicket != null) {
                Boolean isMailSent = emailService.sendTicketConfirmedEmail(savedTicket);
                if (isMailSent) {
                    return ResponseEntity.ok(savedTicket);
                } else {
                    throw new EmailSendingException("Failed to send the ticket confirmation email");
                }
            } else {
                throw new ResourceNotFoundException("Failed to save the ticket");
            }
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (EmailSendingException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @GetMapping
    public String test() {
        return "Hello";
    }
}
