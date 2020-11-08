package com.sms.be.repository.base;

import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class AbstractCustomQuery {
    
    @PersistenceContext
    protected EntityManager entityManager;

    protected <T> Page<T> getPage(JPQLQuery<T> query, int pageOffset, int pageSize) {
        Pageable pageable = PageRequest.of(pageOffset, pageSize);
        List<T> resultList = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        long totalCount = query.fetchCount();
        return new PageImpl<>(resultList, pageable, totalCount);
    }
}
