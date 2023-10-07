package com.wellnr.commons.functions;


import com.wellnr.commons.Operators;

@FunctionalInterface
public interface Function0<R> {

    R apply() throws Exception;

    default R get() {
        return Operators.suppressExceptions(this::apply);
    }

}
