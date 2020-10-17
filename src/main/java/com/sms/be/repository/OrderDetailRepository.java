package com.sms.be.repository;

import com.sms.be.model.OrderDetail;
import com.sms.be.model.embedded.OrderDetailId;
import com.sms.be.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends BaseRepository<OrderDetail, OrderDetailId> {
}
