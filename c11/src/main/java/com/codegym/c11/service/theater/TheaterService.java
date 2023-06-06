package com.codegym.c11.service.theater;

import com.codegym.c11.model.dto.ITheaterDto;
import com.codegym.c11.model.dto.response.PageResponseDto;
import com.codegym.c11.model.dto.response.TheaterResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TheaterService {
    List<TheaterResponseDto> getTheaters();
    List<ITheaterDto> getMovieInTheater(String id);
    PageResponseDto<?> findAll(Pageable pageable);
}
