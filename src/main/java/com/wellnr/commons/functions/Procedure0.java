/*
 * (C) Copyright 2023. Licensed under the Apache License, Version 2.0.
 * Author: Michael Wellner (https://github.com/cokeSchlumpf/).
 */
package com.wellnr.commons.functions;

import com.wellnr.commons.Operators;

@FunctionalInterface
public interface Procedure0 {

    void apply() throws Exception;

    default void run() {
        Operators.suppressExceptions(this::apply);
    }
}
