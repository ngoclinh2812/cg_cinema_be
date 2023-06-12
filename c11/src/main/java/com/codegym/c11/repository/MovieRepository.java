package com.codegym.c11.repository;

import com.codegym.c11.model.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(value = "select m from Movie m ")
    Page<Movie> findAllFilm(Pageable pageable);

    @Query(value = "select * from movie where name like %:name% ORDER BY name ASC", nativeQuery = true)
    Page<Movie> findByName(@Param("name") String name, Pageable pageable);


    void deleteAllById(Long id);
}