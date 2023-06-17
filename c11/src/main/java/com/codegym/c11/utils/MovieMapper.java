package com.codegym.c11.utils;

import com.codegym.c11.model.dto.response.MovieResponseDto;

import com.codegym.c11.model.entity.Movie;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieMapper {
    public MovieResponseDto entitiesDto(Movie movie){
           MovieResponseDto movieDtos = new MovieResponseDto();
           BeanUtils.copyProperties(movie, movieDtos);
           return movieDtos;
        }
    public MovieResponseDto entityToDto (Movie movie){
        MovieResponseDto movieDto = new MovieResponseDto();
        BeanUtils.copyProperties(movie, movieDto);
        return movieDto;
    }
    public Movie DtoToEntity (MovieResponseDto movieDto){
        Movie movie = new Movie();
        BeanUtils.copyProperties(movieDto, movie);
        return movie;
    }

    public  List<MovieResponseDto> toListDto(List<Movie> content) {
        return content.stream().map(ele -> entitiesDto( ele)).collect(Collectors.toList());
    }

}
