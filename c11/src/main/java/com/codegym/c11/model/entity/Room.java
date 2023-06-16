package com.codegym.c11.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "room")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    private Theater TheaterId;

    @OneToMany(mappedBy = "room", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Seat> seatList;

    @OneToMany(mappedBy = "room")
    private List<ScheduleMovie> scheduleMovieList;

}
