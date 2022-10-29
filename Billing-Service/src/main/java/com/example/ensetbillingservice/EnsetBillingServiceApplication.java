package com.example.ensetbillingservice;

import com.example.ensetbillingservice.dtos.InvoiceRequestDTO;
import com.example.ensetbillingservice.services.InvoiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;

@EnableFeignClients
@SpringBootApplication
public class EnsetBillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnsetBillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(InvoiceService invoiceService){
        return args -> {
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(98000),"C01"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(56600),"C02"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(150000),"C03"));
        };
    }
}
