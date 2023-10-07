package com.wellnr.commons.functions;

import com.wellnr.commons.Operators;

@FunctionalInterface
public interface Procedure0 {

  void apply() throws Exception;

  default void run() {
    Operators.suppressExceptions(this::apply);
  }
}
