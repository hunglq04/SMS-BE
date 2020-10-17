package com.sms.be.repository;

import com.sms.be.model.Order;
import com.sms.be.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends BaseRepository<Order, Long> {
}
