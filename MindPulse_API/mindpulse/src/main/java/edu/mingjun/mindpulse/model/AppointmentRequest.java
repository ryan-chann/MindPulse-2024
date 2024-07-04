package edu.mingjun.mindpulse.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentRequest {
    private String therapistId;
    private String fullName;
    private String nric;
    private String phoneNumber;
    private String emailAddress;
    private String mode;
    private String session;
    private String therapistName;
    private String therapistType;
    private String totalPrice;
    private String selectedDate;
    private String selectedTime;
}