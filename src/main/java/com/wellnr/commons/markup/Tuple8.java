/*
 * (C) Copyright 2023. Licensed under the Apache License, Version 2.0.
 * Author: Michael Wellner (https://github.com/cokeSchlumpf/).
 */
package com.wellnr.commons.markup;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(staticName = "apply")
public class Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> {

    public T1 _1;

    public T2 _2;

    public T3 _3;

    public T4 _4;

    public T5 _5;

    public T6 _6;

    public T7 _7;

    public T8 _8;
}
