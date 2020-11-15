package com.sms.be.service.impl;

import com.sms.be.service.core.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Throwable.class)
public class CustomerServiceImpl implements CustomerService {
}
