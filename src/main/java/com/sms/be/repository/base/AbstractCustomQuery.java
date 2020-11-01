package com.sms.be.repository.base;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractCustomQuery {
    @PersistenceContext
    protected EntityManager entityManager;
}
