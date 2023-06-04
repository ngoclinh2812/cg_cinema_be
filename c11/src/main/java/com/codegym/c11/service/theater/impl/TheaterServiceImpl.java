package com.codegym.c11.service.theater.impl;

import com.codegym.c11.model.dto.ITheaterDto;
import com.codegym.c11.model.dto.response.TheaterResponseDto;
import com.codegym.c11.model.entity.Theater;
import com.codegym.c11.repository.TheaterRepository;
import com.codegym.c11.service.theater.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TheaterServiceImpl implements TheaterService {

    private final TheaterRepository theaterRepository;

    @Override
    public List<TheaterResponseDto> getTheaters() {
        List<Theater> theaters = theaterRepository.listTheaters();
        List<TheaterResponseDto> theaterResponseDtos = new ArrayList<>();
        for (Theater theater : theaters){
            TheaterResponseDto responseDto =new TheaterResponseDto();
            BeanUtils.copyProperties(theater, responseDto);
            theaterResponseDtos.add(responseDto);
        }
        return theaterResponseDtos;
    }

    @Override
    public List<ITheaterDto> getMovieInTheater(String id) {
        List<ITheaterDto> iTheaterDtoList = theaterRepository.findTheaterById(Long.valueOf(id));
        return iTheaterDtoList;
    }
}
