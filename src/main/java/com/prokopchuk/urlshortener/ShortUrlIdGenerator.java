package com.prokopchuk.urlshortener;

import java.io.Serializable;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class ShortUrlIdGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o)
      throws HibernateException {
        return RandomStringUtils.randomAlphanumeric(7);
    }
}
