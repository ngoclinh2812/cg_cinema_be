package com.codegym.c11.service.sf.room;

import com.codegym.c11.model.dto.response.IRoomDto;

import java.util.List;

public interface RoomService {
    List<IRoomDto> findAllSeatByIdRoom(Long id);
}
