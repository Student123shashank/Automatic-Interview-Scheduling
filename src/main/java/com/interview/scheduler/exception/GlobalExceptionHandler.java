package com.interview.scheduler.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SlotAlreadyBookedException.class)
    public ResponseEntity<String> handleSlotBooked() {
        return ResponseEntity.badRequest().body("Slot already booked");
    }
}
