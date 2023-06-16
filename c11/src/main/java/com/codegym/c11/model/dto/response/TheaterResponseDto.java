package com.codegym.c11.model.dto.response;

import com.codegym.c11.model.entity.Room;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TheaterResponseDto {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String img;
    private List<RoomResponseDto> roomListDto;
}
