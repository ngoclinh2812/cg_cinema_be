package com.codegym.c11.model.dto.request.Ticket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class SeatDto {
    private Long id;
}
