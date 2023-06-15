package com.codegym.c11.model.dto.Ticket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

// Dto dùng để test chức năng tạo file pdf, ko đưa vào sử dụng
@JsonIgnoreProperties(ignoreUnknown = false)
@Data
public class TicketRequestDtoTest {
    private String ticketCode;
    private String movieTitle;
    private String cinema;
    private String screeningRoom;
    private String showTime;
    private String seats;
}
