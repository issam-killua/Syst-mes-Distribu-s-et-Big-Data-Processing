package com.example.ensetbillingservice.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String messages) {
        super(messages);
    }
}
