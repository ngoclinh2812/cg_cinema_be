package com.codegym.c11.utils;

import com.codegym.c11.model.dto.Ticket.request.ScheduleDto;
import com.codegym.c11.model.entity.Schedule;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapper {
    public Schedule requestDtoToEntity (ScheduleDto scheduleDto){
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDto, schedule);
        return schedule;
    }
}
