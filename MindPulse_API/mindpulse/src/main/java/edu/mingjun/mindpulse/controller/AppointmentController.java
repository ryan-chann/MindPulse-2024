package edu.mingjun.mindpulse.controller;

import edu.mingjun.mindpulse.model.AppointmentRequest;
import edu.mingjun.mindpulse.model.ServiceOffered;
import edu.mingjun.mindpulse.service.AppointmentService;
import edu.mingjun.mindpulse.service.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final FileUploadService fileUploadService;

    private byte[] fileBytes;
    private String fileName;

    public AppointmentController(){
        this.appointmentService = new AppointmentService();
        this.fileUploadService = new FileUploadService();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/getAllServiceOffered")
    public List<ServiceOffered> getAllServiceOffered() {
        return appointmentService.getAllServiceOffered();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/getServiceOffered/{serviceId}/{therapistType}")
    public Map<String, Double> getServicePriceByIdAndTherapistType(@PathVariable String serviceId, @PathVariable String therapistType) {
        return appointmentService.getServicePriceByIdandTherapistType(serviceId, Integer.parseInt(therapistType));
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/getTaxRate")
    public Double getTaxRate(){
        return appointmentService.getTaxRate();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/submit")
    public void submitBookingApplication(@RequestBody AppointmentRequest appointmentRequest){
        this.appointmentService.updateTherapistUnavailableSlot(appointmentRequest.getTherapistId(), appointmentRequest.getSelectedTime(), appointmentRequest.getSelectedDate());
//        System.out.println(STR."Therapist Type \{appointmentRequest.getTherapistType()}");
//        System.out.println(STR."Therapist Name \{appointmentRequest.getTherapistName()}");
//        System.out.println(STR."Mode \{appointmentRequest.getMode()}");
//        System.out.println(STR."Session \{appointmentRequest.getSession()}");
//        System.out.println(STR."Selected Date \{appointmentRequest.getSelectedDate()}");
//        System.out.println(STR."Selected Time \{appointmentRequest.getSelectedTime()}");
//        System.out.println(STR."Full Name \{appointmentRequest.getFullName()}");
//        System.out.println(STR."Email Address \{appointmentRequest.getEmailAddress()}");
//        System.out.println(STR."NRIC \{appointmentRequest.getNric()}");
//        System.out.println(STR."Phone Number \{appointmentRequest.getPhoneNumber()}");
//        System.out.println(STR."Total Price \{appointmentRequest.getTotalPrice()}");
        boolean fileSaved = this.saveFile();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/saveFile")
    public boolean saveFile() {
        return fileUploadService.uploadFile(this.fileBytes, this.fileName);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            this.fileBytes = file.getBytes();
            this.fileName = file.getOriginalFilename();

            System.out.println(fileName);

            if (fileName == null || fileName.isEmpty()) {
                return new ResponseEntity<>("File name is missing", HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(String.format("File uploaded successfully: %s", fileName), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(String.format("Failed to upload file: %s", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
