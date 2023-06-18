package com.codegym.c11.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "movie")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private String duration;

    private String trailer;

    private String img;

    private Integer rating;

    private String genre;

    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnd;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ScheduleMovie> scheduleMovieList;
}
