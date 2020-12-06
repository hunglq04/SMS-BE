package com.sms.be.service.impl;

import com.sms.be.dto.request.ProductRequest;
import com.sms.be.dto.response.ProductResponse;
import com.sms.be.exception.ProductTypeNotFound;
import com.sms.be.model.*;
import com.sms.be.repository.ProductRepository;
import com.sms.be.repository.ProductTypeRepository;
import com.sms.be.service.core.ProductService;
import com.sms.be.utils.MapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.NumberUtils;

import javax.rmi.CORBA.Util;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@Transactional(rollbackFor = Throwable.class)
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Override
    public List<ProductResponse> getAllProduct(String name) {
        List<Product> products = StringUtils.isEmpty(name) ?
                productRepository.findAll() :
                productRepository.findByNameContains(name);
        return products.stream().map(MapperUtils::productToProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductResponse> getProductPage(int pageOffset, int pageSize) {
        return null;
    }

    @Override
    public void addNewProduct(ProductRequest productRequest) {
        ProductType productType = productTypeRepository.findById(productRequest.getProductTypeId())
                .orElseThrow(() -> new ProductTypeNotFound("No product type found"));
        Product product = Product.builder()
                .productType(productType)
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .image(productRequest.getImageUrl())
                .description(productRequest.getDescription())
                .build();
        productRepository.save(product);
    }
    @Override
    public Optional<Product> getProduct(Long id){
        return productRepository.findById(id);
    }
    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void updateProduct(ProductRequest productRequest, Long id) {
        ProductType productType = productTypeRepository.findById(productRequest.getProductTypeId())
                .orElseThrow(() -> new ProductTypeNotFound("No product type found"));
        Optional<Product> products = productRepository.findById(id);
        products.get().setProductType(productType);
        products.get().setName(productRequest.getName());
        products.get().setImage(productRequest.getImageUrl());
        products.get().setPrice(productRequest.getPrice());
        products.get().setDescription(productRequest.getDescription());
        productRepository.save(products.get());
    }
}
