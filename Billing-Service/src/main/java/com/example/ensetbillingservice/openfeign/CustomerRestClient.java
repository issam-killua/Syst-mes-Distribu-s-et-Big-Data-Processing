package com.example.ensetbillingservice.openfeign;


import com.example.ensetbillingservice.entities.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {
    @GetMapping(path="/api/customers/{id}")
    public Customer getCustomer(@PathVariable(name="id") String id);
    @GetMapping(path="/api/customers")
    List<Customer> Allcustomers();
}
