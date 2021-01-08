package com.sms.be.service.core;

import com.sms.be.dto.request.CustomerRequest;
import com.sms.be.model.Customer;

import java.util.Optional;

public interface CustomerService {
    Optional<Customer> getcustomer();
    void changeInfo(CustomerRequest customerRequest);
}
