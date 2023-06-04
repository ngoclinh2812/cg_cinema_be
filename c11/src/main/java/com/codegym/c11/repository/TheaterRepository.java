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

    @Query(value = "select DISTINCT t.id as theaterId, t.name as theaterName, m.name as movieName, s.show_time as scheduleShow_time," +
            " s.show_date as scheduleShow_date, r.name as roomName" +
            " from movie m" +
            " inner join theater_movie tm on tm.id = m.id" +
            " inner join theater t on t.id = tm.theaterId" +
            " INNER JOIN schedule_movie sm ON  sm.movieId = m.id" +
            " INNER JOIN schedule s ON s.id = sm.scheduleId" +
            " INNER JOIN room_movie rm ON rm.movieId = m.id " +
            " INNER JOIN room r ON  r.id = rm.movieId" +
            " WHERE t.id =:id", nativeQuery = true,
            countQuery = "select count(*) " +
                    "  from movie m" +
                    "  inner join theater_movie tm on tm.id = m.id" +
                    "  inner join theater t on t.id = tm.theaterId" +
                    "  INNER JOIN schedule_movie sm ON  sm.movieId = m.id" +
                    "  INNER JOIN schedule s ON s.id = sm.scheduleId" +
                    "  INNER JOIN room_movie rm ON rm.movieId = m.id" +
                    "  INNER JOIN room r ON  r.id = rm.movieId" +
                    " WHERE t.id =:id")
    List<ITheaterDto> findTheaterById(@Param("id") Long id);
}
