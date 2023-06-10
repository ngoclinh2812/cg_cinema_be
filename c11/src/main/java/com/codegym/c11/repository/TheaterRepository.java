package com.codegym.c11.repository;

import com.codegym.c11.model.dto.response.ITheaterDto;
import com.codegym.c11.model.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {
    @Query("SELECT th from Theater th")
    List<Theater> listTheaters();

    @Query(value = "SELECT sm.id as theaterId, m.name AS movieName, s.show_time AS scheduleShow_time, s.show_date AS scheduleShow_date, r.name AS roomName, t.name AS theaterName\n" +
            "FROM schedule_movie sm\n" +
            "JOIN movie m ON sm.movieId = m.id\n" +
            "JOIN schedule s ON sm.scheduleId = s.id\n" +
            "JOIN room r ON sm.room_id = r.id\n" +
            "JOIN theater t ON r.theater_id = t.id " +
            "where t.id = ?1;", nativeQuery = true)
    List<ITheaterDto> findTheaterById(Long id);
}
