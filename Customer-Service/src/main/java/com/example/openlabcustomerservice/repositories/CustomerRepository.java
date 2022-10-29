package com.example.openlabcustomerservice.repositories;

import com.example.openlabcustomerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
