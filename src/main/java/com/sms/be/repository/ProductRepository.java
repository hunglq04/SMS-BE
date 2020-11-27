package com.sms.be.repository;

import com.sms.be.model.Product;
import com.sms.be.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {
    List<Product> findByNameContains(String name);
}
