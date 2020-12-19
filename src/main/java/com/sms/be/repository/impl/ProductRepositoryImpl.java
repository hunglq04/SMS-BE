package com.sms.be.repository.impl;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.sms.be.model.Product;
import com.sms.be.model.QProduct;

import com.sms.be.repository.base.AbstractCustomQuery;
import com.sms.be.repository.custom.ProductRepositoryCustom;
import org.springframework.data.domain.Page;

public class ProductRepositoryImpl extends AbstractCustomQuery implements ProductRepositoryCustom {

    @Override
    public Page<Product> getProductPage(int pageOffset, int pageSize) {
            JPQLQuery<Product> query = new JPAQuery<>(entityManager)
                    .from(QProduct.product)
                    .orderBy(QProduct.product.id.asc())
                    .select(QProduct.product);
        return getPage(query, pageOffset, pageSize);
    }
}
