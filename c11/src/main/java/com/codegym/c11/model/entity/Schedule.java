package com.codegym.c11.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "schedule")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "show_time", nullable = false)
    private LocalDateTime showTime;

    @Column(name = "show_date", nullable = false)
    private LocalDateTime showDate;

    @OneToMany(mappedBy = "schedule")
    private List<ScheduleMovie> scheduleMovieList;
}
