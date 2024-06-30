/*
 * (C) Copyright 2024. Licensed under the Apache License, Version 2.0.
 * Author: Michael Wellner (https://github.com/cokeSchlumpf/).
 */
package com.wellnr.commons.exceptions;

/**
 * A generic root domain exception. A domain exception is an exception which is intended to be presented to the user.
 * The root cause usually is due to invalid user inputs or invalid state/ responses from other systems.
 */
public class DomainException extends RuntimeException {

    protected DomainException(String message) {
        super(message);
    }

    protected DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
