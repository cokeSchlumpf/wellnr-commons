/*
 * (C) Copyright 2023. Licensed under the Apache License, Version 2.0.
 * Author: Michael Wellner (https://github.com/cokeSchlumpf/).
 */
package com.wellnr.commons.functions;

@FunctionalInterface
public interface Function6<T1, T2, T3, T4, T5, T6, R> {

    R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) throws Exception;
}
