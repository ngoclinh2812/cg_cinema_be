package com.codegym.c11.controller;


import com.codegym.c11.model.dto.ITheaterDto;
import com.codegym.c11.model.dto.response.PageResponseDto;
import com.codegym.c11.model.dto.response.TheaterResponseDto;
import com.codegym.c11.service.theater.impl.TheaterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/theaters")
public class TheaterController {
    @Autowired
    private TheaterServiceImpl theaterService;

    @GetMapping("")
    private ResponseEntity<PageResponseDto<TheaterResponseDto>> getTheaters(@PageableDefault(value = 4)Pageable pageable){
        return new ResponseEntity<>(theaterService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<List<ITheaterDto>> getTheaterShowingMovies(@PathVariable String id){
        List<ITheaterDto> theaterDtoList = theaterService.getMovieInTheater(id) ;
        if (theaterDtoList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(theaterDtoList, HttpStatus.OK);
    }
}
