package com.codegym.c11.utils;

import com.codegym.c11.model.dto.request.TheaterRequestDto;
import com.codegym.c11.model.dto.response.TheaterResponseDto;
import com.codegym.c11.model.entity.Theater;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TheaterMapper {

    public TheaterResponseDto responseDtoToEntity(Theater theater) {
        TheaterResponseDto theaterResponseDto = new TheaterResponseDto();
        BeanUtils.copyProperties(theater, theaterResponseDto);
        return theaterResponseDto;
    }

    public Theater entityToResponseDto(TheaterResponseDto theaterResponseDto){
        Theater theater = new Theater();
        BeanUtils.copyProperties(theaterResponseDto, theater);
        return theater;
    }

    public Theater entityToRequestDto(TheaterRequestDto theaterRequestDto){
        Theater theater = new Theater();
        BeanUtils.copyProperties(theaterRequestDto, theater);
        return theater;
    }
}
