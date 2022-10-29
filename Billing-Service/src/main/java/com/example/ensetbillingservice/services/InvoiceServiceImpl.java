package com.example.ensetbillingservice.services;

import com.example.ensetbillingservice.exceptions.CustomerNotFoundException;
import com.example.ensetbillingservice.dtos.InvoiceRequestDTO;
import com.example.ensetbillingservice.dtos.InvoiceResponseDTO;
import com.example.ensetbillingservice.entities.Customer;
import com.example.ensetbillingservice.entities.Invoice;
import com.example.ensetbillingservice.mappers.InvoiceMapper;
import com.example.ensetbillingservice.openfeign.CustomerRestClient;
import com.example.ensetbillingservice.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceRepository invoiceRepository;
    private CustomerRestClient customerRestClient;
    private InvoiceMapper invoiceMapper;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository,
                              CustomerRestClient customerRestClient,
                              InvoiceMapper invoiceMapper) {
        this.invoiceRepository = invoiceRepository;
        this.customerRestClient = customerRestClient;
        this.invoiceMapper = invoiceMapper;
    }

    @Override
    public InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO) {
        /*
        verification de l'Integrite Referentielle Invoice / Customer
         */
        Customer customer = null;
        try{
            customer = customerRestClient.getCustomer(invoiceRequestDTO.getCustomerId());
        }catch (Exception e){
            throw new CustomerNotFoundException("Customer Not found");
        }
        Invoice invoice = invoiceMapper.fromInvoiceRequestDTO(invoiceRequestDTO);
        invoice.setId(UUID.randomUUID().toString());
        invoice.setDate(new Date());

        Invoice savedInvoice = invoiceRepository.save(invoice);
        savedInvoice.setCustomer(customer);
        return invoiceMapper.fromInvoice(savedInvoice);
    }

    @Override
    public InvoiceResponseDTO getInvoice(String invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).get();
        Customer customer = customerRestClient.getCustomer(invoice.getCustomerId());
        invoice.setCustomer(customer);
        return invoiceMapper.fromInvoice(invoice);
    }

    @Override
    public List<InvoiceResponseDTO> invoicesByCustomerId(String customerId) {
        List<Invoice> invoices = invoiceRepository.findByCustomerId(customerId);
        for(Invoice invoice:invoices){
            Customer customer = customerRestClient.getCustomer(invoice.getCustomerId());
            invoice.setCustomer(customer);
        };
        return invoices.stream().map(invoice -> invoiceMapper
                .fromInvoice(invoice))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceResponseDTO> allinvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        for(Invoice invoice:invoices){
                Customer customer = customerRestClient.getCustomer(invoice.getCustomerId());
                invoice.setCustomer(customer);
            };
        return invoices.stream().map(inv->invoiceMapper.fromInvoice(inv)).collect(Collectors.toList());
    }


}
