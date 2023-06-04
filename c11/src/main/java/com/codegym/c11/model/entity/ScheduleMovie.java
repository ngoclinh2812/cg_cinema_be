package com.codegym.c11.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "schedule_movie")
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "movieId", insertable = false, updatable = false)
    private long movieId;
    @Column(name = "scheduleId", insertable = false, updatable = false)
    private long scheduleId;

    @ManyToOne
    @JoinColumn(nullable = false,name = "movieId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Movie movie;

    @ManyToOne
    @JoinColumn(nullable = false,name = "scheduleId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Schedule schedule;

}
