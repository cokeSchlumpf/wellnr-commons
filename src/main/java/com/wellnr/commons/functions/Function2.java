package com.wellnr.commons.functions;

import com.wellnr.commons.Operators;

@FunctionalInterface
public interface Function2<T1, T2, R> {

    R apply(T1 t1, T2 t2) throws Exception;

    default R get(T1 t1, T2 t2) {
        return Operators.suppressExceptions(() -> this.apply(t1, t2));
    }

}
