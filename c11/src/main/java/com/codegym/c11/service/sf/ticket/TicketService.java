package com.codegym.c11.service.sf.ticket;

import com.codegym.c11.model.dto.Ticket.TicketResponseDto;
import com.codegym.c11.model.dto.Ticket.request.TicketRequestDto;
import com.codegym.c11.model.dto.response.PageResponseDto;
import com.codegym.c11.model.entity.Ticket;

public interface TicketService {


    Ticket save(TicketRequestDto ticketEntity);

    PageResponseDto<TicketResponseDto> getTicketByUser(String username);

}
