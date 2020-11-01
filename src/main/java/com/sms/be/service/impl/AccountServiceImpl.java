package com.sms.be.service.impl;

import com.sms.be.dto.AccountDto;
import com.sms.be.dto.request.RegisterRequest;
import com.sms.be.exception.RoleNotFound;
import com.sms.be.model.Account;
import com.sms.be.model.Role;
import com.sms.be.repository.AccountRepository;
import com.sms.be.repository.RoleRepository;
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

    @Override
    public void createNewAccount(RegisterRequest request) {
        List<Role> roles = roleRepository.findByNameIn(request.getRoles());
        if (roles.isEmpty()) {
            throw new RoleNotFound();
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(request.getPassword());
        Account account = new Account(request.getUsername(), password, roles);
        accountRepository.save(account);
    }
}
