package com.codegym.c11.utils;

import com.codegym.c11.model.dto.response.MovieResponseDto;

import com.codegym.c11.model.entity.Movie;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class MovieMapper {
    public Page<MovieResponseDto> entitiesDto(Page<Movie> films){
        List<MovieResponseDto> movieDtos = new ArrayList<>();
        for (Movie element : films){
            MovieResponseDto movieDto = entityToDto (element);
            movieDtos.add(movieDto);
        }
        return new PageImpl<>(movieDtos, films.getPageable(), films.getTotalElements());
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
}
