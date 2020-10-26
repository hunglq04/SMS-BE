package com.sms.be.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/internal")
public class InternalController {
    @GetMapping("/test")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String test() {
        return "api client work!";
    }
}
