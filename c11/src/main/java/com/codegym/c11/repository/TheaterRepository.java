package com.codegym.c11.repository;

import com.codegym.c11.model.dto.ITheaterDto;
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

    @Query(value = "SELECT DISTINCT t.id as theaterId,  t.name AS theaterName, m.name AS movieName, s.show_time, s.show_date, r.name AS roomName " +
            "FROM schedule_movie sm " +
            "INNER JOIN movie m ON sm.movieId = m.id " +
            "INNER JOIN schedule s ON sm.scheduleId = s.id " +
            "INNER JOIN room r ON sm.room_id = r.id " +
            "INNER JOIN theater t ON r.theater_id = t.id " +
            "WHERE t.id = :id ;", nativeQuery = true,
            countQuery = "SELECT count(*) "+
                    "FROM schedule_movie sm " +
                    "INNER JOIN movie m ON sm.movieId = m.id " +
                    "INNER JOIN schedule s ON sm.scheduleId = s.id " +
                    "INNER JOIN room r ON sm.room_id = r.id " +
                    "INNER JOIN theater t ON r.theater_id = t.id " +
                    " WHERE t.id = :id ")
    List<ITheaterDto> findTheaterById(@Param("id") Long id);
}
