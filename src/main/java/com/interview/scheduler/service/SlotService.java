package com.interview.scheduler.service;

import com.interview.scheduler.entity.Interviewer;
import com.interview.scheduler.entity.Slot;
import com.interview.scheduler.entity.User;
import com.interview.scheduler.exception.SlotAlreadyBookedException;
import com.interview.scheduler.repository.SlotRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

@Service
public class SlotService {

    private final SlotRepository slotRepository;

    public SlotService(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    public List<Slot> getSlots(LocalDateTime cursor) {
        return slotRepository.findBySlotTimeAfterOrderBySlotTime(cursor);
    }

   
    public boolean canBookThisWeek(Interviewer interviewer, LocalDateTime slotTime) {

        int week = slotTime.get(
                WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear()
        );

        long count = slotRepository.findAll().stream()
                .filter(s -> s.getInterviewer().getId().equals(interviewer.getId()))
                .filter(Slot::isBooked)
                .filter(s ->
                        s.getSlotTime().get(
                                WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear()
                        ) == week
                )
                .count();

        return count < interviewer.getMaxInterviewsPerWeek();
    }

   
    @Transactional
    public void bookSlot(Long slotId, User user) {

        if (user == null) {
            throw new RuntimeException("Login required");
        }

        Slot slot = slotRepository.findById(slotId)
                .orElseThrow();

        if (slot.isBooked()) {
            throw new SlotAlreadyBookedException();
        }

        if (!canBookThisWeek(slot.getInterviewer(), slot.getSlotTime())) {
            throw new RuntimeException("Weekly interview limit reached");
        }

        slot.setBooked(true);
        slotRepository.save(slot);
    }


    public void generateSlots(Interviewer interviewer) {
        LocalDateTime now = LocalDateTime.now();

        for (int i = 0; i < 14; i++) {
            Slot slot = new Slot();
            slot.setSlotTime(now.plusDays(i).withHour(10));
            slot.setInterviewer(interviewer);
            slotRepository.save(slot);
        }
    }
}
