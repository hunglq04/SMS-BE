package com.sms.be.repository;

import com.sms.be.model.Product;
import com.sms.be.repository.base.BaseRepository;
import com.sms.be.repository.custom.ProductRepositoryCustom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long>, ProductRepositoryCustom {
    List<Product> findByNameContains(String name);
    List<Product> findProductById(Long id);
}
