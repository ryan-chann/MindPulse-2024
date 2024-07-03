package edu.mingjun.mindpulse.controller;

import edu.mingjun.mindpulse.model.ServiceOffered;
import edu.mingjun.mindpulse.service.AppointmentService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(){
        this.appointmentService = new AppointmentService();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/getAllServiceOffered")
    public List<ServiceOffered> getAllServiceOffered() {
        return appointmentService.getAllServiceOffered();
    }
}
