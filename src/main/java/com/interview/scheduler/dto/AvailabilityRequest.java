package com.interview.scheduler.dto;

import lombok.*;

import java.time.LocalTime;

@Getter @Setter
public class AvailabilityRequest {

    private String dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
}
