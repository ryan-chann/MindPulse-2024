package edu.mingjun.mindpulse.service;

import edu.mingjun.mindpulse.model.ServiceOffered;
import edu.mingjun.mindpulse.repository.OfferedServiceRepository;
import edu.mingjun.mindpulse.repository.TaxRateRepository;
import edu.mingjun.mindpulse.repository.TherapistAvailabilityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    private final OfferedServiceRepository offeredServiceRepository;
    private final TaxRateRepository taxRateRepository;
    private final TherapistAvailabilityRepository therapistAvailabilityRepository;

    public AppointmentService() {
        this.offeredServiceRepository = new OfferedServiceRepository();
        this.taxRateRepository = new TaxRateRepository();
        this.therapistAvailabilityRepository = new TherapistAvailabilityRepository();
    }

    public void updateTherapistUnavailableSlot(String id, String time, String date){
        String dateTimeString = STR."\{date}T\{time}";
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString);
        this.therapistAvailabilityRepository.updateOneUnavailableSlotWithTherapistId(id,localDateTime);
    }

    public ServiceOffered getServiceOfferedById(String id){
        return this.offeredServiceRepository.findById(id);
    }

    public Map<String, Double> getServicePriceByIdandTherapistType(String serviceId, Integer therapistType) {
        ServiceOffered s1 = this.getServiceOfferedById(serviceId);

        String therapistTypeString = switch(therapistType){
            case 1 -> "ClinicalPsychologist";
            case 2 -> "Counsellor";
            case 3 -> "Trainee";
            default -> "Invalid";
        };

        return s1.getRate().entrySet()
                .stream()
                .filter(entry -> entry.getValue().containsKey(therapistTypeString))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().get(therapistTypeString)
                ));
    }

    public Double getTaxRate () {
        return taxRateRepository.findTaxRate().getValue();
    }

    public List<ServiceOffered> getAllServiceOffered(){
        return this.offeredServiceRepository.findAll();
    }
}
