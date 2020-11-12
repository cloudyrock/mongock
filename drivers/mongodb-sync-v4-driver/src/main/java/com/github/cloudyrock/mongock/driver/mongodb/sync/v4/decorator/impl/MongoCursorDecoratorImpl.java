package com.github.cloudyrock.mongock.driver.mongodb.sync.v4.decorator.impl;

import com.github.cloudyrock.mongock.driver.mongodb.sync.v4.decorator.MongoCursorDecorator;
import com.mongodb.client.MongoCursor;
import io.changock.driver.api.lock.guard.invoker.LockGuardInvoker;

public class MongoCursorDecoratorImpl<T> implements MongoCursorDecorator<T> {

  private final MongoCursor<T> impl;
  private final LockGuardInvoker checker;

  public MongoCursorDecoratorImpl(MongoCursor<T> implementation, LockGuardInvoker lockerCheckInvoker) {
    this.impl = implementation;
    this.checker = lockerCheckInvoker;
  }

  @Override
  public MongoCursor<T> getImpl() {
    return impl;
  }

  @Override
  public LockGuardInvoker getInvoker() {
    return checker;
  }
}
