package edu.mingjun.mindpulse.controller;

import edu.mingjun.mindpulse.model.Customer;
import edu.mingjun.mindpulse.controller.CustomerController;
import edu.mingjun.mindpulse.service.CustomerService;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/customer")
@Controller
public class CustomerControllerImpl implements CustomerController{
    
    private final CustomerService CUSTOMER_SERVICE;

    @Autowired
    public CustomerControllerImpl(CustomerService customerService) {
        this.CUSTOMER_SERVICE = customerService;
    }

    @PostMapping("/create")
    public void create(@RequestBody Customer newCustomer) {
        // CUSTOMER_SERVICE.save(newCustomer);
        System.out.println("Create triggered");
    }

    @GetMapping("/get/{id}")
    public void get(@PathVariable String id) {
        // CUSTOMER_SERVICE.get(id);
        System.out.println("Get triggered");
    }

    @PutMapping("/update/{customer}")
    public void update(@RequestBody Customer newCustomer) {
        // CUSTOMER_SERVICE.update(newCustomer);
        System.out.println("Update triggered");
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        // CUSTOMER_SERVICE.delete(id);
        System.out.println("Delete triggered");
    }
}
