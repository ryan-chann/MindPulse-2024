package edu.mingjun.mindpulse.service;

import edu.mingjun.mindpulse.model.*;
import edu.mingjun.mindpulse.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TherapistProfileService {
    private final TherapistInfoRepository therapistInfoRepository;
    private final TherapistLanguageRepository therapistLanguageRepository;
    private final TherapistModeOfConductRepository therapistModeOfConductRepository;
    private final TherapistEducationRepository therapistEducationRepository;
    private final TherapistAssistanceRepository therapistAssistanceRepository;
    private final TherapistApproachRepository therapistApproachRepository;

    public TherapistProfileService() {
        this.therapistInfoRepository = new TherapistInfoRepository();
        this.therapistLanguageRepository = new TherapistLanguageRepository();
        this.therapistModeOfConductRepository = new TherapistModeOfConductRepository();
        this.therapistEducationRepository = new TherapistEducationRepository();
        this.therapistAssistanceRepository = new TherapistAssistanceRepository();
        this.therapistApproachRepository = new TherapistApproachRepository();
    }

    public List<TherapistInfo> getAllInfo() {
        return therapistInfoRepository.findAll();
    }

    public TherapistProfile getProfileById(String id){
        TherapistInfo therapistInfo = this.therapistInfoRepository.findById(id);
        TherapistLanguage therapistLanguage = this.therapistLanguageRepository.findById(id);
        TherapistModeOfConduct therapistModeOfConduct = this.therapistModeOfConductRepository.findById(id);
        TherapistEducation therapistEducation = this.therapistEducationRepository.findById(id);
        TherapistAssistance therapistAssistance = this.therapistAssistanceRepository.findById(id);
        TherapistApproach therapistApproach = this.therapistApproachRepository.findById(id);

        return TherapistProfile.builder()
                .therapistApproach(therapistApproach)
                .therapistAssistance(therapistAssistance)
                .therapistInfo(therapistInfo)
                .therapistEducation(therapistEducation)
                .therapistLanguage(therapistLanguage)
                .therapistModeOfConduct(therapistModeOfConduct)
                .build();
    }
}