package com.interview.scheduler.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
public class SlotResponse {

    private Long slotId;
    private LocalDateTime slotTime;
    private boolean booked;
}
