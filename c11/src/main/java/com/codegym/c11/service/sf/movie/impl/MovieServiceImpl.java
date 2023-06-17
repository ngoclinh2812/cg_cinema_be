package com.codegym.c11.service.sf.movie.impl;

import com.codegym.c11.model.dto.response.MovieResponseDto;
import com.codegym.c11.model.dto.response.PageResponseDto;
import com.codegym.c11.model.entity.Movie;
import com.codegym.c11.repository.MovieRepository;
import com.codegym.c11.service.sf.movie.MovieService;
import com.codegym.c11.utils.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    @Override
    public PageResponseDto<MovieResponseDto> findAllMovies(Pageable pageable) {
        Page<Movie> movies = movieRepository.findAll(pageable);
        List<MovieResponseDto> movieResponseDtos = mapMoviesToResponseDtos(movies.getContent());
        return new PageResponseDto<>(movies.getTotalPages(), movies.getTotalElements(), movies.getNumber(), movies.getSize(), movieResponseDtos);
    }

    @Override
    public MovieResponseDto findById(Long id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        return movie != null ? movieMapper.entityToDto(movie) : null;
    }

    @Override
    public Movie save(MovieResponseDto movieDto) {
        Movie movie = movieMapper.DtoToEntity(movieDto);
        return movieRepository.save(movie);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public PageResponseDto<MovieResponseDto> findByTitle(String title, Pageable pageable) {
        Page<Movie> movies = movieRepository.findByTitleContainingIgnoreCase(title, pageable);
        List<MovieResponseDto> movieResponseDtos = mapMoviesToResponseDtos(movies.getContent());
        return new PageResponseDto<>(movies.getTotalPages(), movies.getTotalElements(), movies.getNumber(), movies.getSize(), movieResponseDtos);
    }

    @Override
    public PageResponseDto<MovieResponseDto> findOnGoingMovies(Pageable pageable) {
        Page<Movie> movies = movieRepository.findOnGoingMovies(pageable);
        List<MovieResponseDto> movieResponseDtos = mapMoviesToResponseDtos(movies.getContent());
        return new PageResponseDto<>(movies.getTotalPages(), movies.getTotalElements(), movies.getNumber(), movies.getSize(), movieResponseDtos);
    }

    @Override
    public PageResponseDto<MovieResponseDto> findComingSoonMovies(Pageable pageable) {
        Page<Movie> movies = movieRepository.findComingSoonMovies(pageable);
        List<MovieResponseDto> movieResponseDtos = mapMoviesToResponseDtos(movies.getContent());
        return new PageResponseDto<>(movies.getTotalPages(), movies.getTotalElements(), movies.getNumber(), movies.getSize(), movieResponseDtos);
    }

    @Override
    public PageResponseDto<MovieResponseDto> findAllMoviesWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return findAllMovies(pageable);
    }

    @Override
    public PageResponseDto<MovieResponseDto> findMoviesByTitleWithPagination(String title, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        PageResponseDto<MovieResponseDto> responseDto = new PageResponseDto<>();
        Page<Movie> moviePage = movieRepository.findByTitleContainingIgnoreCase(title, pageable);
        List<MovieResponseDto> movieResponseDtos = mapMoviesToResponseDtos(moviePage.getContent());
        responseDto.setTotalPage(moviePage.getTotalPages());
        responseDto.setTotalRecord(moviePage.getTotalElements());
        responseDto.setPage(moviePage.getNumber());
        responseDto.setSize(moviePage.getSize());
        responseDto.setDataList(movieResponseDtos);
        return responseDto;
    }

    // Helper method to map Movie entities to MovieResponseDto
    private List<MovieResponseDto> mapMoviesToResponseDtos(List<Movie> movies) {
        return movies.stream()
                .map(movieMapper::entityToDto)
                .collect(Collectors.toList());
    }
}
