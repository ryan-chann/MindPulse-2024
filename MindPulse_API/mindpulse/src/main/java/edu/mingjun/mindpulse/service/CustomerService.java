package edu.mingjun.mindpulse.service;

import edu.mingjun.mindpulse.model.Customer;
import edu.mingjun.mindpulse.repository.impl.CustomerRepository;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sso.endpoints.internal.Value;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService() {
        this.customerRepository = new CustomerRepository();
    }

    public boolean save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer findByPartitionKey(String uuId) {
        return customerRepository.findByPartitionKey(uuId);
    }

    public boolean update(Customer customer) {
        return customerRepository.update(customer);
    }

    public boolean deleteByPartitionKey(String uuId) {
        return customerRepository.deleteByPartitionKey(uuId);
    }
}
