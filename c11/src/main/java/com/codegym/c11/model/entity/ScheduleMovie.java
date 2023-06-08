package com.codegym.c11.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(nullable = false,name = "movieId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Movie movie;

    @ManyToOne
    @JoinColumn(nullable = false,name = "scheduleId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(nullable = false,name = "room_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Room room;

    @OneToMany(mappedBy = "scheduleMovie")
    private List<Ticket> ticketList;


}
