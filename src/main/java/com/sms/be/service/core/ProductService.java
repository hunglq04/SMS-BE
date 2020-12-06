package com.sms.be.service.core;

import com.sms.be.dto.request.ProductRequest;
import com.sms.be.dto.response.ProductResponse;
import com.sms.be.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void addNewProduct(ProductRequest productRequest);
    List<ProductResponse> getAllProduct(String name);
    Page<ProductResponse> getProductPage(int pageOffset, int pageSize);
    Optional<Product> getProduct(Long id);
    void deleteProduct(Long id);
    void updateProduct(ProductRequest productRequest, Long id);
}
