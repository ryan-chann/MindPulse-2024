package edu.mingjun.mindpulse.service;

import edu.mingjun.mindpulse.model.ServiceOffered;
import edu.mingjun.mindpulse.repository.OfferedServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    private final OfferedServiceRepository offeredServiceRepository;

    public AppointmentService() {
        this.offeredServiceRepository = new OfferedServiceRepository();
    }

    public ServiceOffered getServiceOfferedById(String id){
        return this.offeredServiceRepository.findById(id);
    }

    public List<ServiceOffered> getAllServiceOffered(){
        return this.offeredServiceRepository.findAll();
    }
}
