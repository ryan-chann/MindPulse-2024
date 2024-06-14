package edu.mingjun.mindpulse.service;

import edu.mingjun.mindpulse.model.Customer;
import edu.mingjun.mindpulse.service.CustomerService;
import edu.mingjun.mindpulse.repository.CustomerRepository;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CustomerServiceImpl implements CustomerService {
    
    private final CustomerRepository CUSTOMER_REPOSITORY;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.CUSTOMER_REPOSITORY = customerRepository;
    }

    @Override
    public void save(Customer customer) {
        CUSTOMER_REPOSITORY.save(customer);
    }

    @Override
    public Customer get(String CustomerUuId) {
        return CUSTOMER_REPOSITORY.get(CustomerUuId);
    }

    @Override
    public void update(Customer customer) {
        CUSTOMER_REPOSITORY.save(customer);
    }

    @Override
    public void delete(String CustomerUuId) {
        CUSTOMER_REPOSITORY.delete(CustomerUuId);
    }
}
