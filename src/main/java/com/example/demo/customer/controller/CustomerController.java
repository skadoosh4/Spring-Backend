package com.example.demo.customer.controller;

import com.example.demo.customer.model.Customer;
import com.example.demo.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id){
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            return ResponseEntity.ok(customer.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCustomer(@PathVariable Integer id , @RequestBody Customer customer){
        Customer customerToUpdate = customerRepository.findById(id).get();
        customer.setId(customerToUpdate.getId());
        customerRepository.save(customer);
        return ResponseEntity.ok().build();
    }
}
