package com.codegym.c11.controller.sf_controller;

import com.codegym.c11.model.dto.response.MovieResponseDto;
import com.codegym.c11.model.dto.response.PageResponseDto;
import com.codegym.c11.service.sf.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "${app.cors.allowedOrigins}")
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("")
    public ResponseEntity<PageResponseDto<MovieResponseDto>> findAllMovies(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
            PageResponseDto<MovieResponseDto> movies = movieService.findAllMoviesWithPagination(page, size);
            System.out.println(page);
            System.out.println(size);
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<PageResponseDto<MovieResponseDto>> findByName(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
            PageResponseDto<MovieResponseDto> movieDtos = movieService.findMoviesByTitleWithPagination(title, page, size);
            return new ResponseEntity<>(movieDtos, HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

/*
    @GetMapping("")
    private ResponseEntity<?> fillAll(@PageableDefault() Pageable pageable) {
        try {
            PageResponseDto<MovieResponseDto> movies = movieService.findAllMovies(pageable);
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> findByName(
            @RequestParam(value = "name") String name,
            @PageableDefault() Pageable pageable) {
        try {
            PageResponseDto<MovieResponseDto> movieDtos = movieService.findByName(name, pageable);
            return new ResponseEntity<>(movieDtos, HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
*/

    @GetMapping("/ongoing")
    public ResponseEntity<PageResponseDto<MovieResponseDto>> findOnGoingMovies(){
    PageResponseDto<MovieResponseDto> movies = movieService.findOnGoingMovies(Pageable.unpaged());
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/coming-soon")
    public ResponseEntity<PageResponseDto<MovieResponseDto>> findComingSoonMovie(){
        PageResponseDto<MovieResponseDto> movieDates = movieService.findComingSoonMovies(Pageable.unpaged());
        return new ResponseEntity<>(movieDates, HttpStatus.OK);
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

