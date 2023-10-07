/*
 * (C) Copyright 2023. Licensed under the Apache License, Version 2.0.
 * Author: Michael Wellner (https://github.com/cokeSchlumpf/).
 */
package com.wellnr.commons.markup;

import lombok.AllArgsConstructor;
import lombok.Value;

/** Markup class to indicate that some function completed successfully. */
@Value
@AllArgsConstructor(staticName = "getInstance")
public class Done {}
