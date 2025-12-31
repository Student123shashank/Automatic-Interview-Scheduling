package com.interview.scheduler.dto;

import java.time.LocalDateTime;

public class UpdateSlotRequest {

    private LocalDateTime slotTime;

    public LocalDateTime getSlotTime() {
        return slotTime;
    }

    public void setSlotTime(LocalDateTime slotTime) {
        this.slotTime = slotTime;
    }
}
