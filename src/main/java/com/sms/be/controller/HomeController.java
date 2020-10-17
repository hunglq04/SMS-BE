package com.sms.be.controller;

import com.sms.be.model.Account;
import com.sms.be.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//TODO remove this test and
@CrossOrigin(origins = {"http://localhost:4200", "https://sms-fe.web.app", "https://sms-fe.firebaseapp.com"})
@RestController
public class HomeController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/")
    public String home() {
        return "Herokuuuuuuuuuu";
    }

    @GetMapping("/account/{username}")
    public Account getAccountInfo(@PathVariable String username) {
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
}
