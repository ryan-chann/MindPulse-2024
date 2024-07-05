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

    public String getFormattedTherapistType() {
        return switch (Integer.parseInt(this.therapistType)) {
            case 1 -> "Clinical Psychologist";
            case 2 -> "Counsellor";
            case 3 -> "Trainee";
            default -> "Invalid";
        };
    }

    public String getFormattedSessionNames() {
        if (this.getSession().equals("returningSession")) {
            return "Returning Session";
        } else if (this.getSession().equals("firstSession")) {
            return "First Session";
        }
        return "Invalid";
    }

    public String getFormattedModeNames() {
        if (this.getMode().equals("inPerson")) {
            return "In-Person";
        } else if (this.getMode().equals("online")) {
            return "Online";
        }
        return "Invalid";
    }
}