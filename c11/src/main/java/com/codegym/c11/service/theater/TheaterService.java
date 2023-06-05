package com.codegym.c11.service.theater;

import com.codegym.c11.model.dto.ITheaterDto;
import com.codegym.c11.model.dto.response.TheaterResponseDto;

import java.util.List;

public interface TheaterService {
    List<TheaterResponseDto> getTheaters();
    List<ITheaterDto> getMovieInTheater(String id);
}
