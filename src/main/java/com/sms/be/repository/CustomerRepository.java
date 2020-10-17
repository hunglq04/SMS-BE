package com.sms.be.repository;

import com.sms.be.model.Customer;
import com.sms.be.repository.base.BaseRepository;
import com.sms.be.repository.custom.CustomerRepositoryCustom;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends BaseRepository<Customer, Long>, CustomerRepositoryCustom {
}
