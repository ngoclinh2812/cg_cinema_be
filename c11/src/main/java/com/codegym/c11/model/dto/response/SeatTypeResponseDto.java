package com.codegym.c11.model.dto.response;

import com.codegym.c11.model.entity.Seat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeatTypeResponseDto {
    private Long id;
    private List<SeatResponseDto> seatList;
    private Double price;
}
