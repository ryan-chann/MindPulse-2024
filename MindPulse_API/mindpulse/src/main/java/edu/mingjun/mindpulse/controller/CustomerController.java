package edu.mingjun.mindpulse.controller;

import edu.mingjun.mindpulse.model.Customer;
import edu.mingjun.mindpulse.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/initialize")
    public ResponseEntity<String> initializeTable() {
        return ResponseEntity.ok(customerService.initializeTable());
    }
}
