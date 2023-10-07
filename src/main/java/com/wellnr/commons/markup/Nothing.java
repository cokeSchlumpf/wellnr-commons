/*
 * (C) Copyright 2023. Licensed under the Apache License, Version 2.0.
 * Author: Michael Wellner (https://github.com/cokeSchlumpf/).
 */
package com.wellnr.commons.markup;

/** Markup type to indicate that nothing is present. */
public enum Nothing {
    INSTANCE;

    public static Nothing getInstance() {
        return INSTANCE;
    }
}
