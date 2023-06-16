package com.codegym.c11.model.entity;

import com.codegym.c11.enums.ESeatStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "seat")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_type", nullable = false)
    private SeatType seatType;

    @OneToMany(mappedBy = "seat")
    private List<Ticket> ticketList;

    @Enumerated(EnumType.STRING)
    private ESeatStatus status;
}
