package com.example.openlabcustomerservice.mappers;


import com.example.openlabcustomerservice.dtos.CustomerRequestDTO;
import com.example.openlabcustomerservice.dtos.CustomerResponseDTO;
import com.example.openlabcustomerservice.entities.Customer;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponseDTO CustomerToCustomerResponseDTO(Customer customer);

    Customer CustomerRequestDTOToCustomer(CustomerRequestDTO customerRequestDTO);



}
