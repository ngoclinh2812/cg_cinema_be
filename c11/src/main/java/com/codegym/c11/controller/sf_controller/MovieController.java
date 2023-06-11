package com.codegym.c11.controller.sf_controller;

import com.codegym.c11.model.dto.response.MovieResponseDto;
import com.codegym.c11.model.dto.response.PageResponseDto;
import com.codegym.c11.service.sf.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3001")
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("")
    private ResponseEntity<PageResponseDto<MovieResponseDto>> fillAll(@PageableDefault(value = 10)Pageable pageable){
        return new ResponseEntity<>(movieService.findAllMovies(pageable), HttpStatus.OK);
    }



    @GetMapping("/search")
    public ResponseEntity<PageResponseDto<MovieResponseDto>> findByName(
            @RequestParam(value = "name") String name,
            @PageableDefault(size = 10) Pageable pageable) {
        PageResponseDto<MovieResponseDto> movieDtos = movieService.findByName(name, pageable);
        return new ResponseEntity<>(movieDtos, HttpStatus.OK);
    }
    }


