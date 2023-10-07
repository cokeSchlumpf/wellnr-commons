/*
 * (C) Copyright 2023. Licensed under the Apache License, Version 2.0.
 * Author: Michael Wellner (https://github.com/cokeSchlumpf/).
 */
package com.wellnr.commons.markup;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(staticName = "apply")
public class Tuple2<T1, T2> {

    public T1 _1;

    public T2 _2;
}
