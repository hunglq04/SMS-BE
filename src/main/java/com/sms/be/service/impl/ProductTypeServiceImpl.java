package com.sms.be.service.impl;

import com.sms.be.dto.response.ProductTypeReponse;

import com.sms.be.model.ProductType;
import com.sms.be.repository.ProductTypeRepository;
import com.sms.be.service.core.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Throwable.class)
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    ProductTypeRepository productTypeRepository;
    @Override
    public List<ProductTypeReponse> getAllProductType() {
        return productTypeRepository.findAll().stream()
                .map(productType-> new ProductTypeReponse(productType.getId(), productType.getName()))
                .collect(Collectors.toList());
    }
}
