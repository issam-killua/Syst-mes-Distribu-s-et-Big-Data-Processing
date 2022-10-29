package com.example.openlabcustomerservice.services;

import com.example.openlabcustomerservice.dtos.CustomerRequestDTO;
import com.example.openlabcustomerservice.dtos.CustomerResponseDTO;
import java.util.List;


public interface CustomerService {
    CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO);

    CustomerResponseDTO getCustomer(String id);

    CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO);

    List<CustomerResponseDTO> listCustomers();
}
