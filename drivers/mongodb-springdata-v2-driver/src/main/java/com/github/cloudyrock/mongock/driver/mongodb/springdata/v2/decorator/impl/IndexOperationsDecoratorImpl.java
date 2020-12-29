package com.github.cloudyrock.mongock.driver.mongodb.springdata.v2.decorator.impl;

import com.github.cloudyrock.mongock.driver.mongodb.springdata.v2.decorator.IndexOperationsDecorator;
import com.github.cloudyrock.mongock.driver.api.lock.guard.invoker.LockGuardInvoker;
import org.springframework.data.mongodb.core.index.IndexOperations;

public class IndexOperationsDecoratorImpl implements IndexOperationsDecorator {

    private final IndexOperations impl;
    private final LockGuardInvoker invoker;

    public IndexOperationsDecoratorImpl(IndexOperations impl, LockGuardInvoker invoker) {
        this.impl = impl;
        this.invoker = invoker;
    }
    @Override
    public IndexOperations getImpl() {
        return impl;
    }

    @Override
    public LockGuardInvoker getInvoker() {
        return invoker;
    }
}
