package com.wellnr.commons.functions;

import com.wellnr.commons.Operators;

@FunctionalInterface
public interface Procedure4<T1, T2, T3, T4> {

    void apply(T1 t1, T2 t2, T3 t3, T4 t4) throws Exception;

    default void run(T1 t1, T2 t2, T3 t3, T4 t4) {
        Operators.suppressExceptions(() -> this.apply(t1, t2, t3, t4));
    }

}
