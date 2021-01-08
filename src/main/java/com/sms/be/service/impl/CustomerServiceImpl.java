package com.sms.be.service.impl;

import com.sms.be.dto.request.CustomerRequest;
import com.sms.be.exception.CustomerNotFound;
import com.sms.be.model.Account;
import com.sms.be.model.Customer;
import com.sms.be.repository.CustomerRepository;
import com.sms.be.service.core.CustomerService;
import com.sms.be.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(rollbackFor = Throwable.class)
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Optional<Customer> getcustomer() {
        Account requester = SecurityUtils.getCurrentAccount();
        return customerRepository.findByAccount(requester);
    }

    @Override
    public void changeInfo(CustomerRequest customerRequest) {
        Account requester = SecurityUtils.getCurrentAccount();
        Optional<Customer> customer = customerRepository.findByAccount(requester);
        customer.get().setName(customerRequest.getName());
        customer.get().setAddress(customerRequest.getAddress());
        customer.get().setPhoneNumber(customerRequest.getPhoneNumber());
        customerRepository.save(customer.get());
    }
}
