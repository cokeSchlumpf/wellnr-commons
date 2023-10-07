/*
 * (C) Copyright 2023. Licensed under the Apache License, Version 2.0.
 * Author: Michael Wellner (https://github.com/cokeSchlumpf/).
 */
package com.wellnr.commons.functions;

import com.wellnr.commons.Operators;

@FunctionalInterface
public interface Procedure6<T1, T2, T3, T4, T5, T6> {

    void apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) throws Exception;

    default void run(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
        Operators.suppressExceptions(() -> this.apply(t1, t2, t3, t4, t5, t6));
    }
}
