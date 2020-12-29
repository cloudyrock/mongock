package com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl;

import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.MongoOperationsDecorator;
import com.github.cloudyrock.mongock.driver.api.lock.guard.invoker.LockGuardInvoker;
import org.springframework.data.mongodb.core.MongoOperations;

public class MongoOperationsDecoratorImpl implements MongoOperationsDecorator {

    private final MongoOperations impl;
    private final LockGuardInvoker invoker;

    public MongoOperationsDecoratorImpl(MongoOperations impl, LockGuardInvoker invoker) {
        this.impl = impl;
        this.invoker = invoker;
    }

    @Override
    public MongoOperations getImpl() {
        return impl;
    }

    @Override
    public LockGuardInvoker getInvoker() {
        return invoker;
    }
}
