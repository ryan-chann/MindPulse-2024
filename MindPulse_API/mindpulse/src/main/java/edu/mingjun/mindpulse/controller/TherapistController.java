package edu.mingjun.mindpulse.controller;

import edu.mingjun.mindpulse.model.TherapistInfo;

import edu.mingjun.mindpulse.model.TherapistProfile;
import edu.mingjun.mindpulse.service.TherapistProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/therapist")
public class TherapistController {

    private final TherapistProfileService therapistProfileService;

    public TherapistController(){
        this.therapistProfileService = new TherapistProfileService();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/getAllInfo")
    public List<TherapistInfo> getAllInfo(){
        return therapistProfileService.getAllInfo();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/getProfile/{id}")
    public TherapistProfile getProfileById(@PathVariable String id){
        return therapistProfileService.getProfileById(id);
    }
}
