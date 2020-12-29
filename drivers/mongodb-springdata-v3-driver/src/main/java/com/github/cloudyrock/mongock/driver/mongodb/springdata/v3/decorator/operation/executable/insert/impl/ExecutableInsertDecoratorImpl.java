package com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.operation.executable.insert.impl;

import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.operation.executable.insert.ExecutableInsertDecorator;
import com.github.cloudyrock.mongock.driver.api.lock.guard.decorator.DecoratorBase;
import com.github.cloudyrock.mongock.driver.api.lock.guard.invoker.LockGuardInvoker;
import org.springframework.data.mongodb.core.ExecutableInsertOperation;

public class ExecutableInsertDecoratorImpl<T>
    extends DecoratorBase<ExecutableInsertOperation.ExecutableInsert<T>>
    implements ExecutableInsertDecorator<T> {
  public ExecutableInsertDecoratorImpl(ExecutableInsertOperation.ExecutableInsert<T> impl, LockGuardInvoker invoker) {
    super(impl, invoker);
  }
}
