package edu.mingjun.mindpulse.controller;

import edu.mingjun.mindpulse.model.api.ApiResponse;
import edu.mingjun.mindpulse.model.Customer;
import edu.mingjun.mindpulse.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public ApiResponse save(@RequestBody Customer customer) {
        boolean isSaved = customerService.save(customer);

        if (isSaved) {
            return ApiResponse.builder()
                    .isSuccess(true)
                    .transaction("POST")
                    .message("Customer saved successfully")
                    .data(customer)
                    .build();
        }else {
            return ApiResponse.builder()
                    .isSuccess(false)
                    .transaction("POST")
                    .message("Unable to save customer")
                    .data(customer)
                    .build();
        }
    }

    @GetMapping("/find")
    public ApiResponse findByPartitionKey(@RequestParam String uuId) {
        Customer customer = customerService.findByPartitionKey(uuId);
        if (customer != null) {
            return ApiResponse.builder()
                    .isSuccess(true)
                    .transaction("GET")
                    .message("Customer found")
                    .data(customer)
                    .build();
        }else {
            return ApiResponse.builder()
                    .isSuccess(false)
                    .transaction("GET")
                    .message("Customer not found")
                    .data(Customer.builder()
                            .uuId(uuId)
                            .build())
                    .build();
        }
    }

    @DeleteMapping("/delete")
    public ApiResponse delete(@RequestParam String uuId) {
        if(customerService.deleteByPartitionKey(uuId)) {
            return ApiResponse.builder()
                    .isSuccess(true)
                    .transaction("DELETE")
                    .message("Customer deleted")
                    .data(Customer.builder()
                            .uuId(uuId)
                            .build())
                    .build();
        }else {
            return ApiResponse.builder()
                    .isSuccess(false)
                    .transaction("DELETE")
                    .message("Unable to delete customer")
                    .data(Customer.builder()
                            .uuId(uuId)
                            .build())
                    .build();
        }
    }
}
