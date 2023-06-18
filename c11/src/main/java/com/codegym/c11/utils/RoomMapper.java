package com.codegym.c11.utils;

import com.codegym.c11.model.dto.request.MovieRequestDto;
import com.codegym.c11.model.dto.request.RoomRequestDto;
import com.codegym.c11.model.entity.Movie;
import com.codegym.c11.model.entity.Room;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {
    public Room requestDtoToEntity (RoomRequestDto roomDto){
        Room room = new Room();
        BeanUtils.copyProperties(roomDto, room);
        return room;
    }
}
