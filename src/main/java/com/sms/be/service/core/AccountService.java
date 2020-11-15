package com.sms.be.service.core;

import com.sms.be.dto.AccountDto;
import com.sms.be.dto.request.RegisterRequest;

public interface AccountService {
    void createNewAccount(RegisterRequest request);
    AccountDto loginSocial(RegisterRequest registerRequest);
}
