package com.codegym.c11.service.sf.seat.impl;

import com.codegym.c11.enums.ESeatStatus;
import com.codegym.c11.model.entity.Seat;
import com.codegym.c11.repository.SeatRepository;
import com.codegym.c11.service.sf.seat.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeatServiceImpl implements ISeatService {
    @Autowired
    private SeatRepository seatRepository;

    @Override
    public Seat getSeatById(Long id) {
        Optional<Seat> seat = seatRepository.findById(id);
        if (seat.isPresent() && seat.get().getStatus() == ESeatStatus.EMPTY) {
            return seat.get();
        }
        return null;
    }
}
