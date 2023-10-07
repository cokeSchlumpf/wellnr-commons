package com.wellnr.commons.functions;

import com.wellnr.commons.Operators;

@FunctionalInterface
public interface Procedure3<T1, T2, T3> {

    void apply(T1 t1, T2 t2, T3 t3) throws Exception;

    default void run(T1 t1, T2 t2, T3 t3) {
        Operators.suppressExceptions(() -> this.apply(t1, t2, t3));
    }

}
