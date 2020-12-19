package com.sms.be.repository.custom;

import com.sms.be.model.Product;
import org.springframework.data.domain.Page;

public interface ProductRepositoryCustom {
    Page<Product> getProductPage(int pageOffset, int pageSize);
}
