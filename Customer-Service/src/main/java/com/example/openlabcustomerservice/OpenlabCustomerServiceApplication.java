package com.example.openlabcustomerservice;

import com.example.openlabcustomerservice.dtos.CustomerRequestDTO;
import com.example.openlabcustomerservice.services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OpenlabCustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenlabCustomerServiceApplication.class, args);}

    @Bean
    CommandLineRunner start(CustomerService customerService) {
        return args -> {
            customerService.save(new CustomerRequestDTO("C01", "Issam", "issam@gmail.com"));
            customerService.save(new CustomerRequestDTO("C02", "Hassan", "hassan@gmail.com"));
            customerService.save(new CustomerRequestDTO("C03", "Younes", "younes@gmail.com"));
        };
    }

}
