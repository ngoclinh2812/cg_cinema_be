/*
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
<<<<<<< HEAD
        List<MovieResponseDto> movieResponseDtos = mapMoviesToResponseDtos(movies.getContent());
        return new PageResponseDto<>(movies.getTotalPages(), movies.getTotalElements(), movies.getNumber(), movies.getSize(), movieResponseDtos);
=======
        responseDtos.setTotalRecord(movies.getTotalElements());
        responseDtos.setTotalPage(movies.getTotalPages());
        responseDtos.setPage(movies.getNumber());
        responseDtos.setSize(movies.getSize());
        responseDtos.setDataList(movieMapper.toListDto(movies.getContent()));

        // Kiểm tra và đánh dấu trạng thái của bộ phim coming soon
        List<MovieResponseDto> dataList = responseDtos.getDataList();
        for (MovieResponseDto movieDto : dataList) {
            LocalDate currentDate = LocalDate.now();
            String movieStatus = checkMovieStatus(movieDto.getDateStart(), movieDto.getDateEnd(), currentDate);
            Date dateEnd = movieDto.getDateEnd();
        }
        return responseDtos;
    }

    @Override
    public String checkMovieStatus(Date dateStart, Date dateEnd, LocalDate currentDate) {
        LocalDate startDate = dateStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = dateEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (currentDate.isBefore(startDate)) {
            return "Phim sắp chiếu";
        } else if (currentDate.isEqual(startDate) || (currentDate.isAfter(startDate) && currentDate.isBefore(endDate))) {
            return "Phim đang chiếu";
        }
        return "";
>>>>>>> origin/dev
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
*/

package com.codegym.c11.service.sf.movie.impl;



import com.codegym.c11.model.dto.response.MovieResponseDto;
import com.codegym.c11.model.dto.response.PageResponseDto;
import com.codegym.c11.model.entity.Movie;
import com.codegym.c11.repository.MovieRepository;
import com.codegym.c11.service.sf.movie.MovieService;
import com.codegym.c11.utils.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
    public PageResponseDto<MovieResponseDto> findByTitle(String name, Pageable pageable) {
        return null;
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

    @Override
    public PageResponseDto<MovieResponseDto> findOnGoingMovies(Pageable pageable) {
        PageResponseDto<MovieResponseDto> responseDtos = new PageResponseDto<>();
        Page<Movie> movies = movieRepository.findOnGoingMovies(pageable);
        responseDtos.setTotalRecord(movies.getTotalElements());
        responseDtos.setTotalPage(movies.getTotalPages());
        responseDtos.setPage(movies.getNumber());
        responseDtos.setSize(movies.getSize());
        responseDtos.setDataList(movieMapper.toListDto(movies.getContent()));

        // Kiểm tra và đánh dấu trạng thái của bộ phim ongoing
        List<MovieResponseDto> dataList = responseDtos.getDataList();
        for (MovieResponseDto movieDto : dataList) {
            LocalDate currentDate = LocalDate.now();
            String movieStatus = checkMovieStatus(movieDto.getDateStart(), movieDto.getDateEnd(), currentDate);
            Date dateStart = movieDto.getDateEnd();
        }
        return responseDtos;
    }

    @Override
    public PageResponseDto<MovieResponseDto> findComingSoonMovies(Pageable pageable) {
        PageResponseDto<MovieResponseDto> responseDtos = new PageResponseDto<>();
        Page<Movie> movies = movieRepository.findComingSoonMovies(pageable);
        responseDtos.setTotalRecord(movies.getTotalElements());
        responseDtos.setTotalPage(movies.getTotalPages());
        responseDtos.setPage(movies.getNumber());
        responseDtos.setSize(movies.getSize());
        responseDtos.setDataList(movieMapper.toListDto(movies.getContent()));

        // Kiểm tra và đánh dấu trạng thái của bộ phim coming soon
        List<MovieResponseDto> dataList = responseDtos.getDataList();
        for (MovieResponseDto movieDto : dataList) {
            LocalDate currentDate = LocalDate.now();
            String movieStatus = checkMovieStatus(movieDto.getDateStart(), movieDto.getDateEnd(), currentDate);
            Date dateEnd = movieDto.getDateEnd();
        }
        return responseDtos;
    }

    @Override
    public PageResponseDto<MovieResponseDto> findAllMoviesWithPagination(int page, int size) {
        return null;
    }

    @Override
    public PageResponseDto<MovieResponseDto> findMoviesByTitleWithPagination(String title, int page, int size) {
        return null;
    }

    @Override
    public String checkMovieStatus(Date dateStart, Date dateEnd, LocalDate currentDate) {
        LocalDate startDate = dateStart.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = dateEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (currentDate.isBefore(startDate)) {
            return "Phim sắp chiếu";
        } else if (currentDate.isEqual(startDate) || (currentDate.isAfter(startDate) && currentDate.isBefore(endDate))) {
            return "Phim đang chiếu";
        }
        return "";
    }


}