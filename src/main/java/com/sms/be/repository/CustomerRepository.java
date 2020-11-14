package com.sms.be.repository;

import com.sms.be.model.Account;
import com.sms.be.model.Customer;
import com.sms.be.repository.base.BaseRepository;
import com.sms.be.repository.custom.CustomerRepositoryCustom;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends BaseRepository<Customer, Long>, CustomerRepositoryCustom {
    Optional<Customer> findByAccount(Account account);
}
