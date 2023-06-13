package com.codegym.c11.repository;

import com.codegym.c11.model.dto.Ticket.request.ScheduleMovieDto;
import com.codegym.c11.model.entity.ScheduleMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleMovieRepository extends JpaRepository<ScheduleMovie, Long> {
    @Query(value = "SELECT * " +
            "FROM schedule_movie " +
            "WHERE movie_id = :movieid " +
            "    AND schedule_id = :scheduleid " +
            "    AND room_id = :roomid", nativeQuery = true)
    Optional<ScheduleMovie> searchByMovieRoomSchedule(@Param("movieid") Long movieId, @Param("scheduleid") Long scheduleId, @Param("roomid") Long roomId);

}
