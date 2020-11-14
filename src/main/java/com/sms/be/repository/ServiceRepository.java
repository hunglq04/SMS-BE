package com.sms.be.repository;

import com.sms.be.model.Service;
import com.sms.be.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends BaseRepository<Service, Long> {
    List<Service> findAllByIdIn(List<Long> serviceIds);
}
