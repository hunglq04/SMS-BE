package com.sms.be.repository;

import com.sms.be.model.Ward;
import com.sms.be.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WardRepository extends BaseRepository<Ward, Long> {
}
