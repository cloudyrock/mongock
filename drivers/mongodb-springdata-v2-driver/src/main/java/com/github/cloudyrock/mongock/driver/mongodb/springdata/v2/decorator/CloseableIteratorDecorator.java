package com.github.cloudyrock.mongock.driver.mongodb.springdata.v2.decorator;

import com.github.cloudyrock.mongock.driver.mongodb.v3.decorator.ChangockIterator;
import org.springframework.data.util.CloseableIterator;

public interface CloseableIteratorDecorator<T> extends CloseableIterator<T>, ChangockIterator<T> {

  CloseableIterator<T> getImpl();

  @Override
  default void close() {
    getInvoker().invoke(() -> getImpl().close());
  }
}
