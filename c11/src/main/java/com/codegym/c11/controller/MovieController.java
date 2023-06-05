package com.codegym.c11.controller;

import com.codegym.c11.model.dto.response.MovieResponseDto;
import com.codegym.c11.model.dto.response.TheaterResponseDto;
import com.codegym.c11.service.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/show")
public class MovieController {
    @Autowired
    private final MovieService movieService;

    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }


    @GetMapping("")
    private ResponseEntity<List<MovieResponseDto>> fillAll(){
        return new ResponseEntity<>(movieService.findAllMovies(), HttpStatus.OK);
    }


    @GetMapping("/list")
    public ResponseEntity<Page<MovieResponseDto>> findByName(@RequestParam(value = "name", defaultValue = "") String name,
                                                             @PageableDefault(size = 3) Pageable pageable){
        Page<MovieResponseDto> movieDtos = movieService.findByName(name, pageable);
        if (movieDtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(movieDtos, HttpStatus.OK);
    }

}
