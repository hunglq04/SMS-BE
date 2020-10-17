package com.sms.be.repository;

import com.sms.be.model.Service;
import com.sms.be.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends BaseRepository<Service, Long> {
}
