package com.codegym.c11.service.sf.theater;

import com.codegym.c11.model.dto.response.ITheaterDto;
import com.codegym.c11.model.dto.response.PageResponseDto;
import com.codegym.c11.model.dto.response.TheaterResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TheaterService {
    List<TheaterResponseDto> getTheaters();
    List<ITheaterDto> getMovieInTheater(Long id);
    PageResponseDto<?> findAll(Pageable pageable);
}
