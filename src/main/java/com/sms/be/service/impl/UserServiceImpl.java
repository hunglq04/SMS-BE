package com.sms.be.service.impl;

import com.sms.be.model.Account;
import com.sms.be.repository.AccountRepository;
import com.sms.be.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String idCard) {
        Optional<Account> account = accountRepository.findByUsername(idCard);
        if (account.isPresent()) {
            return new CustomUserDetails(account.get());
        }
        throw new UsernameNotFoundException(idCard);
    }

    public UserDetails loadUserById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            return new CustomUserDetails(account.get());
        }
        throw new UsernameNotFoundException("Not found user with ID=" + id);
    }
}
