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
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("")
    private ResponseEntity<?> fillAll(@PageableDefault(value = 10) Pageable pageable) {
        try {
            PageResponseDto<MovieResponseDto> movies = movieService.findAllMovies(pageable);
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> findByName(
            @RequestParam(value = "name") String name,
            @PageableDefault(size = 10) Pageable pageable) {
        try {
            PageResponseDto<MovieResponseDto> movieDtos = movieService.findByName(name, pageable);
            return new ResponseEntity<>(movieDtos, HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
    @GetMapping("/{movieId}")
    public ResponseEntity<?> findById(
            @PathVariable(value = "movieId") Long id) {
        try {
            MovieResponseDto movieResponseDto = movieService.findById(id);
            return new ResponseEntity<>(movieResponseDto, HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not find movie.");
        }
    }

}

