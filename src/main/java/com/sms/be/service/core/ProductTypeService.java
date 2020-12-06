package com.sms.be.service.core;

import com.sms.be.dto.response.ProductResponse;
import com.sms.be.dto.response.ProductTypeReponse;
import com.sms.be.model.ProductType;

import java.util.List;

public interface ProductTypeService {
    List<ProductTypeReponse> getAllProductType();
}
