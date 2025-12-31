package com.interview.scheduler.controller;

import com.interview.scheduler.entity.Interviewer;
import com.interview.scheduler.repository.InterviewerRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interviewer")
public class InterviewerController {

    private final InterviewerRepository interviewerRepository;

    public InterviewerController(InterviewerRepository interviewerRepository) {
        this.interviewerRepository = interviewerRepository;
    }

    
    @PostMapping
    public Interviewer createInterviewer(@RequestBody Interviewer interviewer) {
        return interviewerRepository.save(interviewer);
    }

    
    @PostMapping("/{id}/availability")
    public String addAvailability(@PathVariable Long id) {
        return "Availability added for interviewer " + id;
    }
}
