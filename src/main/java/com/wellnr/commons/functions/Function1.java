package com.wellnr.commons.functions;

import com.wellnr.commons.Operators;

@FunctionalInterface
public interface Function1<T, R> {

  R apply(T t) throws Exception;

  default R get(T t) {
    return Operators.suppressExceptions(() -> this.apply(t));
  }
}
