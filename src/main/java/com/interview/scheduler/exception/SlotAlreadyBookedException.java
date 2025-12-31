package com.interview.scheduler.exception;

public class SlotAlreadyBookedException extends RuntimeException {
    public SlotAlreadyBookedException() {
        super("Slot already booked");
    }
}
