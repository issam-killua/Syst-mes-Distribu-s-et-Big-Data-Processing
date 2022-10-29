package com.example.ensetbillingservice.web;


import com.example.ensetbillingservice.dtos.InvoiceRequestDTO;
import com.example.ensetbillingservice.dtos.InvoiceResponseDTO;
import com.example.ensetbillingservice.services.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api")
public class InvoiceRestController {

    private InvoiceService invoiceService;

    public InvoiceRestController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping(path = "/invoices/{id}")
    public InvoiceResponseDTO getInvoice(@PathVariable(name = "id") String invoieId) {
        return invoiceService.getInvoice(invoieId);
    }

    @GetMapping(path = "/invoicesByCustomer/{customerId}")
    public List<InvoiceResponseDTO> getInvoicesByCustomer(@PathVariable String customerId) {
        return invoiceService.invoicesByCustomerId(customerId);
    }

    @PostMapping(path="/invoices")
    public InvoiceResponseDTO save(@RequestBody InvoiceRequestDTO invoiceRequestDTO) {
        return invoiceService.save(invoiceRequestDTO);
    }
    @GetMapping(path = "/invoices")
    public List<InvoiceResponseDTO> allInvoices() {
        return invoiceService.allinvoices();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
