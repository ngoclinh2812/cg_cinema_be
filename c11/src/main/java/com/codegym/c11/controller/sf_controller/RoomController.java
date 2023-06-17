package com.codegym.c11.controller.sf_controller;

import com.codegym.c11.model.dto.response.IRoomDto;
import com.codegym.c11.model.dto.response.ITheaterDto;
import com.codegym.c11.service.sf.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "${app.cors.allowedOrigins}")
@RequestMapping("/api/room")
public class RoomController {
    @Autowired
    private RoomService service;

    @GetMapping("/{id}")
    private ResponseEntity<List<IRoomDto>> getRoomById(@PathVariable("id") String id){
        List<IRoomDto> RoomDtoList = service.findAllSeatByIdRoom(Long.valueOf(id)) ;
        if (RoomDtoList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(RoomDtoList, HttpStatus.OK);
    }
}

