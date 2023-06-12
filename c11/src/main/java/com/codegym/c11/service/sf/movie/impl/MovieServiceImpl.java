package com.codegym.c11.service.sf.movie.impl;



import com.codegym.c11.model.dto.response.MovieResponseDto;
import com.codegym.c11.model.dto.response.PageResponseDto;
import com.codegym.c11.model.entity.Movie;
import com.codegym.c11.repository.MovieRepository;
import com.codegym.c11.service.sf.movie.MovieService;
import com.codegym.c11.utils.MovieMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieMapper movieMapper;

    public MovieServiceImpl(MovieRepository movieRepository, MovieMapper movieMapper){
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    @Override
    public PageResponseDto<MovieResponseDto> findAllMovies(Pageable pageable){
        PageResponseDto<MovieResponseDto> responseDto = new PageResponseDto<>();
        Page<Movie> movies = movieRepository.findAll(pageable);
        responseDto.setTotalRecord(movies.getTotalElements());
        responseDto.setTotalPage(movies.getTotalPages());
        responseDto.setPage(movies.getNumber());
        responseDto.setSize(movies.getSize());
        responseDto.setDataList(movieMapper.toListDto(movies.getContent()));
        return responseDto;

    }
    @Override
    public MovieResponseDto findById(Long id) {
        Movie movie = movieRepository.findById(id).get();
        MovieResponseDto movieDto = movieMapper.entityToDto(movie);
        return movieDto;
    }
    @Override
    public Movie save(MovieResponseDto movieDto) {
        Movie movie = movieMapper.DtoToEntity(movieDto);
        movieRepository.save(movie);
        return null;
    }
    @Override
    @Transactional
    public void remove (Long id){
        movieRepository.deleteAllById(id);
    }


    @Override
    public PageResponseDto<MovieResponseDto> findByName(String name, Pageable pageable) {
        PageResponseDto<MovieResponseDto> responseDtos = new PageResponseDto<>();
        Page<Movie> movies = movieRepository.findAll(pageable);
        responseDtos.setTotalRecord(movies.getTotalElements());
        responseDtos.setTotalPage(movies.getTotalPages());
        responseDtos.setPage(movies.getNumber());
        responseDtos.setSize(movies.getSize());
        responseDtos.setDataList(movieMapper.toListDto(movies.getContent()));
        return responseDtos;
    }


}