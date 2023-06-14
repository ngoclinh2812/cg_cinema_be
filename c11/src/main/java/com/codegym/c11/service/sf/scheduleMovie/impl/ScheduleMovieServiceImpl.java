package com.codegym.c11.service.sf.scheduleMovie.impl;

import com.codegym.c11.model.entity.ScheduleMovie;
import com.codegym.c11.repository.ScheduleMovieRepository;
import com.codegym.c11.service.sf.scheduleMovie.IScheduleMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScheduleMovieServiceImpl implements IScheduleMovieService {
    @Autowired
    private ScheduleMovieRepository scheduleMovieRepository;

    @Override
    public ScheduleMovie getScheduleMovieById(Long id) {
        return scheduleMovieRepository.findById(id).orElse(null);
    }

    @Override
    public ScheduleMovie getScheduleMovieByMovieRoomSchedule(Long movieId, Long roomId, Long scheduleId) {
        Optional<ScheduleMovie> searchScheduleMovie = scheduleMovieRepository.searchByMovieRoomSchedule(movieId, roomId, scheduleId);
        if (searchScheduleMovie.isPresent()) {
            return searchScheduleMovie.get();
        }
        return null;
    }
}
