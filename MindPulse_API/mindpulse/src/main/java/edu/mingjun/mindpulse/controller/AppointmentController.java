package edu.mingjun.mindpulse.controller;

import edu.mingjun.mindpulse.model.AppointmentRequest;
import edu.mingjun.mindpulse.model.ServiceOffered;
import edu.mingjun.mindpulse.service.AppointmentService;
import edu.mingjun.mindpulse.service.EmailService;
import edu.mingjun.mindpulse.service.FileUploadService;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
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

    @Autowired
    private EmailService emailService;

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
        try {
            this.appointmentService.updateTherapistUnavailableSlot(appointmentRequest.getTherapistId(), appointmentRequest.getSelectedTime(), appointmentRequest.getSelectedDate());
            this.emailService.sendBookingConfirmation(appointmentRequest.getEmailAddress(), appointmentRequest);
            boolean fileSaved = this.saveFile();
        } catch (MessagingException | IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
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
