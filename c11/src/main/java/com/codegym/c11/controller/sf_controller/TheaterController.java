package com.codegym.c11.controller.sf_controller;


import com.codegym.c11.model.dto.ITheaterDto;
import com.codegym.c11.model.dto.response.TheaterResponseDto;
import com.codegym.c11.service.sf.theater.impl.TheaterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "${app.cors.allowedOrigins}")
@RequestMapping("/api/theaters")
public class TheaterController {
    @Autowired
    private TheaterServiceImpl theaterService;

    @GetMapping("")
    private ResponseEntity<List<TheaterResponseDto>> getTheaters(){
        return new ResponseEntity<>(theaterService.getTheaters(), HttpStatus.OK);
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
