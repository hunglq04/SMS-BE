package com.sms.be.repository;

import com.sms.be.model.ServiceType;
import com.sms.be.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceTypeRepository extends BaseRepository<ServiceType, Long> {
}
