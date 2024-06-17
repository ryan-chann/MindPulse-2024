package edu.mingjun.mindpulse.service;

import edu.mingjun.mindpulse.repository.impl.CustomerRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String initializeTable() {
        return customerRepository.initializeTable();
    }
}
