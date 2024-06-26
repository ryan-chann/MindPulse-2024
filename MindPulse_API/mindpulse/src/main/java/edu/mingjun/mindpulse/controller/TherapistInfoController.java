package edu.mingjun.mindpulse.controller;

import edu.mingjun.mindpulse.model.TherapistInfo;
import edu.mingjun.mindpulse.service.TherapistInfoService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/therapist")
public class TherapistInfoController {

    private final TherapistInfoService therapistInfoService;

    public TherapistInfoController(){
        this.therapistInfoService = new TherapistInfoService();
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/getAll")
    public List<TherapistInfo> getAllTherapistInfo(){
        return therapistInfoService.getAll();
    }
}
