package com.codegym.c11.model.dto.response;

import com.codegym.c11.model.entity.Seat;
import com.codegym.c11.model.entity.SeatType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public interface IRoomDto {
    String getSeat_name();
    Long getRoom_id();
    Double getPrice();
}
