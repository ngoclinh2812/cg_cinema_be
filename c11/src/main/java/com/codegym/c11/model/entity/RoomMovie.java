package com.codegym.c11.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Table(name = "room_movie")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoomMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "movieId", insertable = false, updatable = false)
    private Long movieId;
    @Column(name = "roomId", insertable = false, updatable = false)
    private Long roomId;

    @ManyToOne
    @JoinColumn(nullable = false,name = "movieId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Movie movie;

    @ManyToOne
    @JoinColumn(nullable = false,name = "roomId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Room room;
}
