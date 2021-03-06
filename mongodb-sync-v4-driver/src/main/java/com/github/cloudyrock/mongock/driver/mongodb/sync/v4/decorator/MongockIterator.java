package com.github.cloudyrock.mongock.driver.mongodb.sync.v4.decorator;

import com.github.cloudyrock.mongock.NonLockGuarded;
import com.github.cloudyrock.mongock.NonLockGuardedType;
import com.github.cloudyrock.mongock.driver.api.lock.guard.invoker.LockGuardInvoker;

import java.util.Iterator;
import java.util.function.Consumer;

public interface MongockIterator<T> extends Iterator<T> {

  Iterator<T> getImpl();

  LockGuardInvoker getInvoker();

  @Override
  default boolean hasNext() {
    return getInvoker().invoke(() -> getImpl().hasNext());
  }

  @Override
  default T next() {
    return getInvoker().invoke(()-> getImpl().next());
  }

  @Override
  @NonLockGuarded(NonLockGuardedType.NONE)
  default void remove() {
    getImpl().remove();
  }


  @Override
  @NonLockGuarded(NonLockGuardedType.NONE)
  default void forEachRemaining(Consumer<? super T> action) {
    getImpl().forEachRemaining(action);
  }
}
