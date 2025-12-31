package com.interview.scheduler.service;

import com.interview.scheduler.entity.Interviewer;
import org.springframework.stereotype.Service;

@Service
public class InterviewerService {

    public Interviewer createInterviewer(String name, int maxPerWeek) {
        Interviewer interviewer = new Interviewer();
        interviewer.setName(name);
        interviewer.setMaxInterviewsPerWeek(maxPerWeek);
        return interviewer;
    }
}
