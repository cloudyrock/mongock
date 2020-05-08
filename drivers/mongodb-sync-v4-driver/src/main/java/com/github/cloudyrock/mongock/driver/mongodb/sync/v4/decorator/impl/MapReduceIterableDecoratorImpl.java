package com.github.cloudyrock.mongock.driver.mongodb.sync.v4.decorator.impl;

import com.mongodb.client.MapReduceIterable;
import io.changock.driver.api.lock.guard.invoker.LockGuardInvoker;
import com.github.cloudyrock.mongock.driver.mongodb.sync.v4.decorator.MapReduceIterableDecorator;

public class MapReduceIterableDecoratorImpl<T> implements MapReduceIterableDecorator<T> {

  private final MapReduceIterable<T> impl;
  private final LockGuardInvoker checker;

  public MapReduceIterableDecoratorImpl(MapReduceIterable<T> implementation, LockGuardInvoker lockerCheckInvoker) {
    this.impl = implementation;
    this.checker = lockerCheckInvoker;
  }

  @Override
  public MapReduceIterable<T> getImpl() {
    return impl;
  }

  @Override
  public LockGuardInvoker getInvoker() {
    return checker;
  }
}
