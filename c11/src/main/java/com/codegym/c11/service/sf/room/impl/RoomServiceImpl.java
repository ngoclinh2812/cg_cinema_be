package com.codegym.c11.service.sf.room.impl;

import com.codegym.c11.model.dto.response.IRoomDto;
import com.codegym.c11.model.dto.response.ITheaterDto;
import com.codegym.c11.repository.RoomRepository;
import com.codegym.c11.service.sf.room.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<IRoomDto> findAllSeatByIdRoom(Long id) {
        return roomRepository.findAllSeatInRoomId(id);
    }
}
