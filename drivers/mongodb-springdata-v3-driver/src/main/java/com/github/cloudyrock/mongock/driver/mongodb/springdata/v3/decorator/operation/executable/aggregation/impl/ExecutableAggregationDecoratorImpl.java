package com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.operation.executable.aggregation.impl;

import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.operation.executable.aggregation.ExecutableAggregationDecorator;
import com.github.cloudyrock.mongock.driver.api.lock.guard.decorator.DecoratorBase;
import com.github.cloudyrock.mongock.driver.api.lock.guard.invoker.LockGuardInvoker;
import org.springframework.data.mongodb.core.ExecutableAggregationOperation;

public class ExecutableAggregationDecoratorImpl<T>
    extends DecoratorBase<ExecutableAggregationOperation.ExecutableAggregation<T>>
    implements ExecutableAggregationDecorator<T> {
  public ExecutableAggregationDecoratorImpl(ExecutableAggregationOperation.ExecutableAggregation<T> impl, LockGuardInvoker invoker) {
    super(impl, invoker);
  }
}
