package com.sms.be.repository;

import com.sms.be.model.OrderDetail;
import com.sms.be.model.embedded.OrderDetailId;
import com.sms.be.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends BaseRepository<OrderDetail, OrderDetailId> {
    List<OrderDetail> findByOrderId(Long orderId);
}
