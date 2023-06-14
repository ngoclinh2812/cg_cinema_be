package com.codegym.c11.model.dto.Ticket;

// Xuat hoa don cho user
public interface TicketResponseDto {
    String getTheater_name();
    String getMovie_name();
    String getRoom_name();
    String getSeat_name();
    String getSchedule_time();
    String getPrice();

}
