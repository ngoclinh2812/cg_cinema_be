package com.codegym.c11.repository;

import com.codegym.c11.model.dto.Ticket.TicketResponseDto;
import com.codegym.c11.model.dto.Ticket.request.TicketRequestDto;
import com.codegym.c11.model.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query(value = "SELECT " +
            "    a.first_name, a.last_name, a.email, " +
            "    m.name AS movie_name, " +
            "    s.show_time, s.show_date, " +
            "    r.name AS room_name, " +
            "    st.type_seat AS seat_type," +
            "    seat.name AS seat_name, " +
            "    st.price AS seat_price " +
            "FROM\n" +
            "    ticket t\n" +
            "    INNER JOIN account a ON t.user_id = a.id\n" +
            "    INNER JOIN schedule_movie sm ON t.schedule_movie_id = sm.id\n" +
            "    INNER JOIN movie m ON sm.movie_id = m.id\n" +
            "    INNER JOIN schedule s ON sm.schedule_id = s.id\n" +
            "    INNER JOIN room r ON sm.room_id = r.id\n" +
            "    INNER JOIN seat seat ON t.seat = seat.id\n" +
            "    INNER JOIN seat_type st ON seat.seat_type = st.id\n" +
            "WHERE\n" +
            "    a.id = :acc" +
            "    AND sm.id = :schedulemovie ;", nativeQuery = true)
    TicketRequestDto getTicket(@Param("acc") Long acc, @Param("schedulemovie") Long schedulemovieid);


    @Query(value = "SELECT a.first_name, " +
            "a.last_name, " +
            "a.email, " +
            "m.name AS movie_name, " +
            "s.show_time, s.show_date, " +
            "r.name AS room_name, " +
            "st.type_seat AS seat_type," +
            "seat.name AS seat_name, " +
            "SUM(st.price) AS total_seat_price " +
            "FROM ticket t " +
            "INNER JOIN account a ON t.user_id = a.id " +
            "INNER JOIN schedule_movie sm ON t.schedule_movie_id = sm.id " +
            "INNER JOIN movie m ON sm.movie_id = m.id " +
            "INNER JOIN schedule s ON sm.schedule_id = s.id " +
            "INNER JOIN room r ON sm.room_id = r.id " +
            "INNER JOIN seat seat ON t.seat = seat.id " +
            "INNER JOIN seat_type st ON seat.seat_type = st.id  " +
            "WHERE a.username = :username " +
            "GROUP BY a.first_name, a.last_name, a.email, m.name, s.show_time, s.show_date, r.name, st.type_seat, seat.name;", nativeQuery = true)
    Page<TicketResponseDto> getTicket(@Param("username") String username, Pageable pageable);

}
