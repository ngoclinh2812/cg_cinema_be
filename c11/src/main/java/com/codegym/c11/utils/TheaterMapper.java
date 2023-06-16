package com.codegym.c11.utils;

import com.codegym.c11.model.dto.request.TheaterRequestDto;
import com.codegym.c11.model.dto.response.RoomResponseDto;
import com.codegym.c11.model.dto.response.TheaterResponseDto;
import com.codegym.c11.model.entity.Room;
import com.codegym.c11.model.entity.Theater;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TheaterMapper {

    public List<TheaterResponseDto> toListDto(List<Theater> content) {
        return content.stream().map(ele -> responseDtoToEntity(ele)).collect(Collectors.toList());
    }

    public List<RoomResponseDto> entityToDto(List<Room> listRoom){
        List<RoomResponseDto> roomResponseDtos = new ArrayList<>();
        for (Room room : listRoom){
            RoomResponseDto roomResponseDto = new RoomResponseDto();
            BeanUtils.copyProperties(room, roomResponseDto);
            roomResponseDtos.add(roomResponseDto);
        }
        return roomResponseDtos;
    }

    public TheaterResponseDto responseDtoToEntity(Theater theater) {
        TheaterResponseDto theaterResponseDto = new TheaterResponseDto();
        BeanUtils.copyProperties(theater, theaterResponseDto);
        return theaterResponseDto;
    }

    public Theater requestDtoToEntity(TheaterRequestDto theaterRequestDto) {
        Theater theater = new Theater();
        BeanUtils.copyProperties(theaterRequestDto, theater);
        return theater;
    }

    public TheaterResponseDto entityToResponseDto(Theater theater){
        TheaterResponseDto theaterResponseDto = new TheaterResponseDto();
        BeanUtils.copyProperties(theater, theaterResponseDto);
        theaterResponseDto.setRoomListDto(entityToDto(theater.getRoomList()));
        return theaterResponseDto;
    }

    public TheaterRequestDto entityToRequestDto(Theater theater){
        TheaterRequestDto theaterRequestDto = new TheaterRequestDto();
        BeanUtils.copyProperties(theater, theaterRequestDto);
        return theaterRequestDto;
    }
}
