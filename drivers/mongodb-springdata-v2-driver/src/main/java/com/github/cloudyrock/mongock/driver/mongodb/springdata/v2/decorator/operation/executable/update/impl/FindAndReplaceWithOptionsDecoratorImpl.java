package com.github.cloudyrock.mongock.driver.mongodb.springdata.v2.decorator.operation.executable.update.impl;

import com.github.cloudyrock.mongock.driver.mongodb.springdata.v2.decorator.operation.executable.update.FindAndReplaceWithOptionsDecorator;
import io.changock.driver.api.lock.guard.decorator.DecoratorBase;
import io.changock.driver.api.lock.guard.invoker.LockGuardInvoker;
import org.springframework.data.mongodb.core.ExecutableUpdateOperation;

public class FindAndReplaceWithOptionsDecoratorImpl<T>
    extends DecoratorBase<ExecutableUpdateOperation.FindAndReplaceWithOptions<T>>
    implements FindAndReplaceWithOptionsDecorator<T> {

  public FindAndReplaceWithOptionsDecoratorImpl(ExecutableUpdateOperation.FindAndReplaceWithOptions<T> impl, LockGuardInvoker invoker) {
    super(impl, invoker);
  }

}
