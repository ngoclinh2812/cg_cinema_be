package com.codegym.c11.repository;

import com.codegym.c11.model.dto.response.IRoomDto;
import com.codegym.c11.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query( value = "SELECT s.name as seat_name, r.id as room_id, st.price as price" +
            " FROM Seat s " +
            " JOIN s.room r" +
            " JOIN s.seatType st" +
            " WHERE r.id = :id ")
    List<IRoomDto> findAllSeatInRoomId(@Param("id") Long id);
}
