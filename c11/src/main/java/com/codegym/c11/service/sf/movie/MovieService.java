package com.codegym.c11.service.sf.movie;

import com.codegym.c11.model.dto.response.MovieResponseDto;
import com.codegym.c11.model.dto.response.PageResponseDto;
import com.codegym.c11.service.sf.GeneralService;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Date;

public interface MovieService extends GeneralService<MovieResponseDto> {
    PageResponseDto<MovieResponseDto> findAllMovies(Pageable pageable);
    PageResponseDto<MovieResponseDto> findByName(String name, Pageable pageable);
    PageResponseDto<MovieResponseDto> findOnGoingMovies(Pageable pageable);
    PageResponseDto<MovieResponseDto> findComingSoonMovies(Pageable pageable);

    String checkMovieStatus(Date dateStart, Date dateEnd, LocalDate currentDate);
}
