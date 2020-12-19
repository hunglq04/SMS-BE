package com.sms.be.repository;

import com.sms.be.model.Service;
import com.sms.be.repository.base.BaseRepository;
import com.sms.be.repository.custom.ServiceRepositoryCustom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends BaseRepository<Service, Long>, ServiceRepositoryCustom {
    List<Service> findAllByIdIn(List<Long> serviceIds);
}
