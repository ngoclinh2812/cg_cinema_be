package com.codegym.c11.service.sf.movie;

import com.codegym.c11.model.dto.response.MovieResponseDto;
import com.codegym.c11.service.sf.GeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService extends GeneralService<MovieResponseDto> {
    List<MovieResponseDto> findAllMovies();
    Page<MovieResponseDto> findByName(String name, Pageable pageable);
}