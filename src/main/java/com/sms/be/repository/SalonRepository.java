package com.sms.be.repository;

import com.sms.be.model.Salon;
import com.sms.be.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalonRepository extends BaseRepository<Salon, Long>, SalonRepositoryCustom {
}
