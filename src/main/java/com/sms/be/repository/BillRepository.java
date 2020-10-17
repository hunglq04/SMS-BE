package com.sms.be.repository;

import com.sms.be.model.Bill;
import com.sms.be.repository.base.BaseRepository;
import com.sms.be.repository.custom.BillRepositoryCustom;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends BaseRepository<Bill, Long>, BillRepositoryCustom {
}
