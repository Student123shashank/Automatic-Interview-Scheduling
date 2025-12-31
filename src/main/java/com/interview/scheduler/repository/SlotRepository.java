package com.interview.scheduler.repository;

import com.interview.scheduler.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SlotRepository extends JpaRepository<Slot, Long> {

    List<Slot> findBySlotTimeAfterOrderBySlotTime(LocalDateTime cursor);
}
