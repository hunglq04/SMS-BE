package com.sms.be.utils;

import com.sms.be.model.Account;
import com.sms.be.repository.CustomerRepository;
import com.sms.be.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class SecurityUtils {

    private SecurityUtils() {
        // Utils class
    }

    public static Account getCurrentAccount() {
        CustomUserDetails customUserDetails = ((CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getDetails());
        if (customUserDetails == null || customUserDetails.getAccount() == null) {
            throw new BadCredentialsException("Account does not exist");
        }
        return customUserDetails.getAccount();
    }
}
