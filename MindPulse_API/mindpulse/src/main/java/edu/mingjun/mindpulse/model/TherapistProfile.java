package edu.mingjun.mindpulse.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TherapistProfile {
    private TherapistInfo therapistInfo;
    private TherapistModeOfConduct therapistModeOfConduct;
    private TherapistLanguage therapistLanguage;
    private TherapistApproach therapistApproach;
    private TherapistAssistance therapistAssistance;
    private TherapistEducation therapistEducation;
}
