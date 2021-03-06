package com.github.cloudyrock.mongock.driver.mongodb.v3.decorator;

import com.github.cloudyrock.mongock.NonLockGuarded;
import com.github.cloudyrock.mongock.NonLockGuardedType;
import com.github.cloudyrock.mongock.driver.mongodb.v3.decorator.impl.ListCollectionsIterableDecoratorImpl;
import com.mongodb.client.ListCollectionsIterable;
import org.bson.conversions.Bson;

import java.util.concurrent.TimeUnit;

public interface ListCollectionsIterableDecorator<T> extends MongoIterableDecorator<T>, ListCollectionsIterable<T> {

  @Override
  ListCollectionsIterable<T> getImpl();

  @Override
  @NonLockGuarded(NonLockGuardedType.METHOD)
  default ListCollectionsIterable<T> filter(Bson filter) {
    return new ListCollectionsIterableDecoratorImpl<>(getImpl().filter(filter), getInvoker());
  }

  @Override
  @NonLockGuarded(NonLockGuardedType.METHOD)
  default ListCollectionsIterable<T> maxTime(long maxTime, TimeUnit timeUnit) {
    return new ListCollectionsIterableDecoratorImpl<>( getImpl().maxTime(maxTime, timeUnit), getInvoker());
  }

  @Override
  @NonLockGuarded(NonLockGuardedType.METHOD)
  default ListCollectionsIterable<T> batchSize(int batchSize) {
    return new ListCollectionsIterableDecoratorImpl<>( getImpl().batchSize(batchSize), getInvoker());
  }

}
