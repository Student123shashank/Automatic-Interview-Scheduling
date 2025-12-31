package com.interview.scheduler.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime slotTime;

    private boolean booked = false;

    @Version
    private Integer version;

    @ManyToOne
    private Interviewer interviewer;
}
