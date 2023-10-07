package com.wellnr.commons.markup;

import lombok.AllArgsConstructor;
import lombok.Value;

/** Markup class to indicate that some function completed successfully. */
@Value
@AllArgsConstructor(staticName = "getInstance")
public class Done {}
