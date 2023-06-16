package com.codegym.c11.service.sf.theater.impl;

import com.codegym.c11.model.dto.response.ITheaterDto;
import com.codegym.c11.model.dto.response.PageResponseDto;
import com.codegym.c11.model.dto.response.TheaterResponseDto;
import com.codegym.c11.model.entity.Theater;
import com.codegym.c11.repository.TheaterRepository;
import com.codegym.c11.service.sf.theater.TheaterService;
import com.codegym.c11.utils.TheaterMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TheaterServiceImpl implements TheaterService {

    private final TheaterRepository theaterRepository;

    private final TheaterMapper theaterMapper;


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
    public List<ITheaterDto> getMovieInTheater(Long id) {
        List<ITheaterDto> iTheaterDtoList = theaterRepository.findTheaterById(id);
        return iTheaterDtoList;
    }

    @Override
    public PageResponseDto<TheaterResponseDto> findAll(Pageable pageable) {
        PageResponseDto<TheaterResponseDto> responseDto = new PageResponseDto<>();
        Page<Theater> theaterPage = theaterRepository.findAll(pageable);
        responseDto.setTotalRecord(theaterPage.getTotalElements());
        responseDto.setTotalPage(theaterPage.getTotalPages());
        responseDto.setPage(theaterPage.getNumber());
        responseDto.setSize(theaterPage.getSize());
        responseDto.setDataList(theaterMapper.toListDto(theaterPage.getContent()));
        return responseDto;
    }
}
