package edu.mingjun.mindpulse.service;

import edu.mingjun.mindpulse.model.TherapistInfo;
import edu.mingjun.mindpulse.repository.TherapistInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TherapistInfoService {
    private TherapistInfoRepository therapistInfoRepository;

    public TherapistInfoService() {
        this.therapistInfoRepository = new TherapistInfoRepository();
    }
    public List<TherapistInfo> getAll() {
        return therapistInfoRepository.findAll();
    }
}
