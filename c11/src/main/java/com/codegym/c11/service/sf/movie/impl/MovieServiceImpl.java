package com.codegym.c11.service.sf.movie.impl;



import com.codegym.c11.model.dto.response.MovieResponseDto;
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
    public Page<MovieResponseDto> findAll (Pageable pageable){
        Page<Movie> films = (Page<Movie>) movieRepository.findAllFilm(pageable);
        Page<MovieResponseDto> dtos = movieMapper.entitiesDto(films);
        return dtos;
    }
    @Autowired
    public List<MovieResponseDto> findAllMovies (){
        List<Movie> movies = movieRepository.findAll();
        List<MovieResponseDto> movieDtos = new ArrayList<>();
        for (Movie movie : movies) {
            MovieResponseDto movieDto = new MovieResponseDto();
            BeanUtils.copyProperties(movie, movieDto);
            movieDtos.add(movieDto);
        }
        return movieDtos;
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
    public Page<MovieResponseDto> findByName(String name, Pageable pageable) {
        Page<Movie> moviePage = movieRepository.findByName(name, pageable);
        List<MovieResponseDto> movieDtos = new ArrayList<>();
        for (Movie movie : moviePage.getContent()) {
            MovieResponseDto movieDto = new MovieResponseDto();
            BeanUtils.copyProperties(movie, movieDto);
            movieDtos.add(movieDto);
        }
        return new PageImpl<>(movieDtos, pageable, moviePage.getTotalElements());
    }


}