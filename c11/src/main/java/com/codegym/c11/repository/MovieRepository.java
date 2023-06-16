package com.codegym.c11.repository;

import com.codegym.c11.model.dto.response.MovieResponseDto;
import com.codegym.c11.model.dto.response.PageResponseDto;
import com.codegym.c11.model.entity.Movie;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(value = "select m from Movie m ")
    Page<Movie> findAllFilm(Pageable pageable);

    @Query(value = "select * from movie where name like %:name% ORDER BY name ASC", nativeQuery = true)
    Page<Movie> findByName(@Param("name") String name, Pageable pageable);

<<<<<<< HEAD
    @Query("select dateStart from Movie")
    List<Date> findDateStartBy();

    @Query(value = "SELECT * FROM movie WHERE date_start ", nativeQuery = true)
    Page<Movie> findOnGoingMovies(Pageable pageable);

    @Query(value = "SELECT * FROM movie WHERE date_end ", nativeQuery = true)
    Page<Movie> findComingSoonMovies(Pageable pageable);


    @Override
    <S extends Movie> boolean exists(Example<S> example);

=======
>>>>>>> eb646a8b304d123be5c917499743ce909760ade1
    void deleteAllById(Long id);

    
}