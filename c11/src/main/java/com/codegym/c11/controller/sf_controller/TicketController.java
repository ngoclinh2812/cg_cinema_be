package com.codegym.c11.controller.sf_controller;

import com.codegym.c11.exception.api.EmailSendingException;
import com.codegym.c11.exception.api.ResourceNotFoundException;
import com.codegym.c11.model.dto.Ticket.TicketResponseDto;
import com.codegym.c11.model.dto.Ticket.request.TicketAccountDto;
import com.codegym.c11.model.dto.Ticket.request.TicketRequestDto;
import com.codegym.c11.model.dto.response.PageResponseDto;
import com.codegym.c11.model.entity.Account;
import com.codegym.c11.model.entity.Ticket;
import com.codegym.c11.service.sf.IAccountService;
import com.codegym.c11.service.sf.email.EmailService;
import com.codegym.c11.service.sf.ticket.TicketService;
import com.codegym.c11.utils.AccountMapper;
import com.codegym.c11.utils.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/ticket")
@CrossOrigin(origins = "${app.cors.allowedOrigins}")
public class TicketController {

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private AccountMapper accountMapper;

    @GetMapping
    public ResponseEntity<?> getUserTickets(HttpServletRequest request) {
        try {
            String username = (String) request.getAttribute("username");
            if (username != null) {
                PageResponseDto<TicketResponseDto> tickets = ticketService.getTicketByUser(username);
                return new ResponseEntity<>(tickets, HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not get ticket. Please try again.");
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> createTicket(@RequestBody TicketRequestDto ticketDto, HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        Account account = accountService.findByUsername(username);

        if (account == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }

        TicketAccountDto accountDto = accountMapper.convertFromEntityToRequestDto(account);
        ticketDto.setAccount(accountDto);

        try {
            Ticket savedTicket = ticketService.save(ticketDto);
            if (savedTicket != null) {
                return ResponseEntity.ok(savedTicket);
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

    @PostMapping("/confirm")
    public ResponseEntity<?> confirmTicket(@RequestBody TicketRequestDto ticketRequestDto) {
        Long ticketId = ticketRequestDto.getId();
        Ticket ticket = ticketService.getTicketById(ticketId);
        if (ticket != null) {
            ticketService.saveConfirmedTicket(ticket);
            return new ResponseEntity<>("Successfully saved", HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
    }


}
