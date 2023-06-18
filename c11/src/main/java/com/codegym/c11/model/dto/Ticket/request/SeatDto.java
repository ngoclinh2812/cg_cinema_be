package com.codegym.c11.model.dto.Ticket.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class SeatDto implements Serializable {
    private Long id;
}
