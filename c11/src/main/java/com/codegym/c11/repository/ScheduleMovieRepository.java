package com.codegym.c11.repository;

import com.codegym.c11.model.entity.ScheduleMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleMovieRepository extends JpaRepository<ScheduleMovie, Long> {
}
