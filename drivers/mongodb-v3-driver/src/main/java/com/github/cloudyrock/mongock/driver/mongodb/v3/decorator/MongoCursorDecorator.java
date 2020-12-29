package com.github.cloudyrock.mongock.driver.mongodb.v3.decorator;

import com.github.cloudyrock.mongock.annotations.NonLockGuarded;
import com.mongodb.ServerAddress;
import com.mongodb.ServerCursor;
import com.mongodb.client.MongoCursor;
import com.github.cloudyrock.mongock.driver.api.lock.guard.invoker.LockGuardInvoker;
import com.github.cloudyrock.mongock.annotations.NonLockGuarded;

public interface MongoCursorDecorator<T> extends MongoCursor<T>, ChangockIterator<T> {

  MongoCursor<T> getImpl();

  LockGuardInvoker getInvoker();

  @Override
  default void close() {
    getInvoker().invoke(() -> getImpl().close());
  }

  @Override
  default boolean hasNext() {
    return getInvoker().invoke(() -> getImpl().hasNext());
  }

  @Override
  default T next() {
    return getInvoker().invoke(() -> getImpl().next());
  }

  @Override
  default T tryNext() {
    return getInvoker().invoke(() -> getImpl().tryNext());
  }

  @Override
  default ServerCursor getServerCursor() {
    return getInvoker().invoke(() -> getImpl().getServerCursor());
  }

  @Override
  @NonLockGuarded
  default ServerAddress getServerAddress() {
    return getImpl().getServerAddress();
  }
}
