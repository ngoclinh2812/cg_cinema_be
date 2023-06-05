package com.codegym.c11.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "theater_movie")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TheaterMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "theaterId", insertable = false, updatable = false)
    private Long theaterId;

    @Column(name = "movieId", insertable = false, updatable = false)
    private Long movieId;

    @ManyToOne
    @JoinColumn(nullable = false,name = "theatersId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Theater theater;

    @ManyToOne
    @JoinColumn(nullable = false,name = "movieId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Movie movie;
}
