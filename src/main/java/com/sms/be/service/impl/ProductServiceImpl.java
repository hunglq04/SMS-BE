package com.sms.be.service.impl;

import com.sms.be.dto.response.ProductResponse;
import com.sms.be.repository.ProductRepository;
import com.sms.be.service.core.ProductService;
import com.sms.be.model.Product;
import com.sms.be.utils.MapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@Transactional(rollbackFor = Throwable.class)
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductResponse> getAllProduct(String name) {
        List<Product> products = StringUtils.isEmpty(name) ?
                productRepository.findAll() :
                productRepository.findByNameContains(name);
        return products.stream().map(MapperUtils::productToProductResponse)
                .collect(Collectors.toList());
    }
}
