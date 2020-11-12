package com.github.cloudyrock.mongock.driver.mongodb.springdata.v2.decorator.operation.executable.insert.impl;

import com.github.cloudyrock.mongock.driver.mongodb.springdata.v2.decorator.operation.executable.insert.TerminatingInsertDecorator;
import io.changock.driver.api.lock.guard.decorator.DecoratorBase;
import io.changock.driver.api.lock.guard.invoker.LockGuardInvoker;
import org.springframework.data.mongodb.core.ExecutableInsertOperation;

public class TerminatingInsertDecoratorImpl<T>
    extends DecoratorBase<ExecutableInsertOperation.TerminatingInsert<T>>
    implements TerminatingInsertDecorator<T> {
  public TerminatingInsertDecoratorImpl(ExecutableInsertOperation.TerminatingInsert<T> impl, LockGuardInvoker invoker) {
    super(impl, invoker);
  }
}
