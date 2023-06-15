package com.codegym.c11.model.dto.response;

import com.codegym.c11.enums.ESeatStatus;
import com.codegym.c11.model.entity.Room;
import com.codegym.c11.model.entity.SeatType;
import com.codegym.c11.model.entity.Ticket;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeatResponseDto {
    private Long id;
    private String name;
    private Room room;
    private SeatType seatType;
    private List<Ticket> ticketList;
    private ESeatStatus status;
}
