package com.codegym.c11.service.sf.scheduleMovie;

import com.codegym.c11.model.entity.ScheduleMovie;

public interface IScheduleMovieService {
    ScheduleMovie getScheduleMovieById(Long id);

    ScheduleMovie getScheduleMovieByMovieRoomSchedule(Long id, Long aLong, Long id1);
}
