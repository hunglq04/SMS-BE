package com.sms.be.service.impl;

import com.sms.be.constant.CommonConstants;
import com.sms.be.dto.AccountDto;
import com.sms.be.dto.request.RegisterRequest;
import com.sms.be.exception.RoleNotFound;
import com.sms.be.exception.SalonNotFoundException;
import com.sms.be.exception.UsernameAlreadyExist;
import com.sms.be.model.*;
import com.sms.be.repository.*;
import com.sms.be.service.core.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SalonRepository salonRepository;

    @Override
    public void createNewAccount(RegisterRequest request) {
        List<Role> roles = roleRepository.findByNameIn(request.getRoles());
        if (roles.isEmpty()) {
            throw new RoleNotFound();
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(request.getPassword());
        accountRepository.findByUsername(request.getUsername())
                .ifPresent(account -> {
                    throw new UsernameAlreadyExist(
                            account.getUsername() + " Already exists");
                });
        Account account = new Account(request.getUsername(), password, roles);
        accountRepository.save(account);
        if (request.getRoles().contains(CommonConstants.ROLE_CUSTOMER)) {
            createCustomer(request, account);
        } else {
            createEmployee(request, account);
        }
    }

    @Override
    public AccountDto loginSocial(RegisterRequest request) {
        String username = accountRepository.findByUsername(request.getUsername())
                .orElseGet(() -> {
                    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                    String password = passwordEncoder.encode(request.getPassword());
                    List<Role> roles = roleRepository.findByNameIn(request.getRoles());
                    Account newAccount = Account.builder()
                            .username(request.getUsername())
                            .password(password)
                            .roles(roles).build();
                    accountRepository.save(newAccount);
                    createCustomer(request, newAccount);
                    return newAccount;
                }).getUsername();
        return new AccountDto(username, request.getPassword());
    }

    private void createEmployee(RegisterRequest request, Account account) {
        Salon salon = salonRepository.findById(request.getSalonId())
                .orElseThrow(SalonNotFoundException::new);
        Employee employee = Employee.builder().account(account)
                .address(request.getAddress()).avatar(request.getAvatar())
                .idCard(request.getIdCard()).name(request.getName())
                .phoneNumber(request.getPhone()).salon(salon)
                .salary(request.getSalary()).build();
        employeeRepository.save(employee);
    }

    private void createCustomer(RegisterRequest request, Account account) {
        String email = request.getUsername().contains("@") ? request.getUsername() : null;
        Customer customer = Customer.builder().account(account)
                .email(request.getUsername())
                .address(request.getAddress()).email(email)
                .name(request.getName()).phoneNumber(request.getPhone())
                .build();
        customerRepository.save(customer);
    }
}
