package com.example.openlabcustomerservice.services;

import com.example.openlabcustomerservice.dtos.CustomerRequestDTO;
import com.example.openlabcustomerservice.dtos.CustomerResponseDTO;
import com.example.openlabcustomerservice.entities.Customer;
import com.example.openlabcustomerservice.mappers.CustomerMapper;
import com.example.openlabcustomerservice.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {


    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) {
        /*
        Mapping Objet Objet
         */

        /*
        Customer customer = new Customer();
        customer.setId(customerRequestDTO.getId());
        customer.setName(customerRequestDTO.getName());
        customer.setEmail(customerRequestDTO.getEmail());
        */

        Customer customer = customerMapper.CustomerRequestDTOToCustomer(customerRequestDTO);
        //customer.setId(UUID.randomUUID().toString());
        Customer savedCustomer = customerRepository.save(customer);
        /*
        Mapping Objet Objet
         */

        /*
        CustomerResponseDTO customerResponseDTO =new CustomerResponseDTO();
        customerResponseDTO.setId(savedCustomer.getId());
        customerResponseDTO.setName(savedCustomer.getName());
        customerResponseDTO.setEmail(savedCustomer.getEmail());
        */

        CustomerResponseDTO customerResponseDTO = customerMapper.CustomerToCustomerResponseDTO(savedCustomer);
        return customerResponseDTO;


    }

    @Override
    public CustomerResponseDTO getCustomer(String id) {
        Customer customer = customerRepository.findById(id).get();
        return customerMapper.CustomerToCustomerResponseDTO(customer);
    }

    @Override
    public CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerMapper.CustomerRequestDTOToCustomer(customerRequestDTO);
        Customer updatedCustomer = customerRepository.save(customer);
        return customerMapper.CustomerToCustomerResponseDTO(updatedCustomer);
    }

    @Override
    public List<CustomerResponseDTO> listCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponseDTO> customerResponseDTOS = customers
                .stream()
                .map(cust -> customerMapper.CustomerToCustomerResponseDTO(cust))
                .collect(Collectors.toList());
        return customerResponseDTOS;
    }
}
