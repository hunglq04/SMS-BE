package com.sms.be.repository;

import com.sms.be.model.ProductType;
import com.sms.be.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends BaseRepository<ProductType, Long> {
}
