package com.sms.be.service.core;

import com.sms.be.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProduct();
}
