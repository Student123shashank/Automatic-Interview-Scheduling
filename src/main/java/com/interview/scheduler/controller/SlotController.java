package com.interview.scheduler.controller;

import com.interview.scheduler.dto.SlotResponse;
import com.interview.scheduler.dto.UpdateSlotRequest;
import com.interview.scheduler.entity.Interviewer;
import com.interview.scheduler.entity.Slot;
import com.interview.scheduler.entity.User;
import com.interview.scheduler.repository.InterviewerRepository;
import com.interview.scheduler.repository.SlotRepository;
import com.interview.scheduler.service.SlotService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/slots")
public class SlotController {

    private final SlotService slotService;
    private final InterviewerRepository interviewerRepository;
    private final SlotRepository slotRepository;

    public SlotController(
            SlotService slotService,
            InterviewerRepository interviewerRepository,
            SlotRepository slotRepository
    ) {
        this.slotService = slotService;
        this.interviewerRepository = interviewerRepository;
        this.slotRepository = slotRepository;
    }

  
    @GetMapping
    public List<SlotResponse> getSlots(@RequestParam LocalDateTime cursor) {
        List<Slot> slots = slotService.getSlots(cursor);
        return slots.stream()
                .map(s -> new SlotResponse(
                        s.getId(),
                        s.getSlotTime(),
                        s.isBooked()
                ))
                .toList();
    }

  
    @PostMapping("/{id}/book")
    public String bookSlot(@PathVariable Long id, HttpSession session) {

        User user = (User) session.getAttribute("LOGGED_IN_USER");
        slotService.bookSlot(id, user);

        return "Slot booked successfully";
    }

  
    @PostMapping("/generate/{interviewerId}")
    public String generateSlots(@PathVariable Long interviewerId) {
        Interviewer interviewer = interviewerRepository
                .findById(interviewerId)
                .orElseThrow();

        slotService.generateSlots(interviewer);
        return "Slots generated for interviewer " + interviewerId;
    }


    @PutMapping("/{id}")
    public Slot updateSlot(
            @PathVariable Long id,
            @RequestBody UpdateSlotRequest request
    ) {
        Slot slot = slotRepository.findById(id).orElseThrow();
        slot.setSlotTime(request.getSlotTime());
        return slotRepository.save(slot);
    }
}
