package com.wellnr.commons.functions;

import com.wellnr.commons.Operators;

@FunctionalInterface
public interface Procedure9<T1, T2, T3, T4, T5, T6, T7, T8, T9> {

    void apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) throws Exception;

    default void run(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
        Operators.suppressExceptions(() -> this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9));
    }

}
