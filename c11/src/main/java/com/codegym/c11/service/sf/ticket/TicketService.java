package com.codegym.c11.service.sf.ticket;

import com.codegym.c11.model.dto.request.Ticket.TicketRequestDto;
import com.codegym.c11.model.entity.Ticket;
import com.codegym.c11.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface TicketService {


    Ticket save(TicketRequestDto ticketEntity);

}
