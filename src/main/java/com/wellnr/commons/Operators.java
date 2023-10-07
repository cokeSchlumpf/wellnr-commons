package com.wellnr.commons;

import com.wellnr.commons.functions.Function0;
import com.wellnr.commons.functions.Procedure0;
import com.wellnr.commons.functions.Procedure1;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.Objects;
import java.util.Optional;

public final class Operators {

    private Operators() {

    }

    /**
     * Checks whether the stack trace contains the given exception class. If yes it returns this instance.
     * Return value is empty if stack trace does not contain the given exception class.
     *
     * @param exception      The exception to check.
     * @param exceptionClass The exception class to check
     * @param <T>            The type of the exception.
     * @return Empty if stack trace does not contain the given exception class.
     */
    @SuppressWarnings("unchecked")
    public static <T> Optional<T> hasCause(Throwable exception, Class<T> exceptionClass) {
        if (exceptionClass.isInstance(exception)) {
            return Optional.of((T) exception);
        } else if (Objects.isNull(exception.getCause())) {
            return Optional.empty();
        } else if (exceptionClass.isInstance(exception.getCause())) {
            return Optional.of((T) exception.getCause());
        } else {
            return hasCause(exception.getCause(), exceptionClass);
        }
    }

    /**
     * Ignores an exception without throwing an exception.
     *
     * @param runnable Method which may throw an exception.
     * @param onError  A procedure which is executed when an exception occurs. Optional.
     */
    public static void ignoreExceptions(Procedure0 runnable, Procedure1<Exception> onError) {
        try {
            runnable.apply();
        } catch (Exception e) {
            if (onError != null) {
                onError.run(e);
            }
        }
    }

    /**
     * Ignores an exception without throwing an exception.
     *
     * @param runnable Method which may throw an exception.
     */
    public static void ignoreExceptions(Procedure0 runnable) {
        ignoreExceptions(runnable, null);
    }

    /**
     * Ignore an exception when executing a function. Return an Optional.empty() if the function throws an exception.
     *
     * @param supplier The function which may throw an exception.
     * @param <T>      Ther return type.
     * @return The return value of the function or Optional.empty() if the function throws an exception.
     */
    public static <T> Optional<T> ignoreExceptionsToOptional(Function0<T> supplier) {
        return ignoreExceptionsToOptional(supplier, null);
    }

    /**
     * Ignore an exception when executing a function. Return an Optional.empty() if the function throws an exception.
     *
     * @param supplier The function which may throw an exception.
     * @param onError  A procedure which is executed when an exception occurs. Optional.
     * @param <T>      Ther return type.
     * @return The return value of the function or Optional.empty() if the function throws an exception.
     */
    public static <T> Optional<T> ignoreExceptionsToOptional(Function0<T> supplier, Procedure1<Exception> onError) {
        try {
            return Optional.of(supplier.apply());
        } catch (Exception e) {
            if (onError != null) {
                onError.run(e);
            }

            return Optional.empty();
        }
    }

    /**
     * Ignore an exception when executing a function. Return a default value if the function throws an exception.
     *
     * @param supplier     The function which may throw an exception.
     * @param defaultValue The default value to return if the function throws an exception.
     * @param onError      A procedure which is executed when an exception occurs. Optional.
     * @param <T>          The return type of the function.
     * @return The return value of the function or the default value if the function throws an exception.
     */
    public static <T> T ignoreExceptionsWithDefault(
        Function0<T> supplier, T defaultValue, Procedure1<Exception> onError
    ) {

        try {
            return supplier.apply();
        } catch (Exception e) {
            if (onError != null) {
                onError.run(e);
            }

            return defaultValue;
        }
    }

    /**
     * Ignore an exception when executing a function. Return a default value if the function throws an exception.
     *
     * @param supplier     The function which may throw an exception.
     * @param defaultValue The default value if the function throws an exception
     * @param <T>          The return type of the function.
     * @return The return value of the function or the default value if the function throws an exception.
     */
    public static <T> T ignoreExceptionsWithDefault(Function0<T> supplier, T defaultValue) {
        return ignoreExceptionsWithDefault(supplier, defaultValue, null);
    }

    /**
     * Suppress all checked exception. These exceptions will be wrapped as Runtime exceptions.
     *
     * @param runnable The method which may throw checked exceptions.
     */
    @SuppressWarnings({"CatchMayIgnoreException", "ResultOfMethodCallIgnored"})
    public static void suppressExceptions(Procedure0 runnable) {
        try {
            runnable.apply();
        } catch (Throwable e) {
            wrapAndThrow(e);
        }
    }

    /**
     * Suppress all checked exception. These exceptions will be wrapped as Runtime exceptions.
     *
     * @param runnable The method which may throw checked exceptions.
     * @param message  The message of the runtime exception.
     */
    public static void suppressExceptions(Procedure0 runnable, String message) {
        try {
            runnable.apply();
        } catch (Exception e) {
            throw new RuntimeException(message, e);
        }
    }

    /**
     * Suppress all checked exception. These exceptions will be wrapped as Runtime exceptions.
     *
     * @param supplier A function which may throw checked exceptions.
     * @param <T>      The type of the function's return value.
     * @return The return value of the function.
     */
    public static <T> T suppressExceptions(Function0<T> supplier) {
        try {
            return supplier.apply();
        } catch (Exception e) {
            if (e instanceof RuntimeException re) {
                throw re;
            } else {
                return wrapAndThrow(e);
            }
        }
    }

    /**
     * Suppress all checked exception. These exceptions will be wrapped as Runtime exceptions.
     *
     * @param supplier The function which may cause exceptions.
     * @param message  The message of the runtime exception.
     * @param <T>      The type of the function's return value.
     * @return The return value of the function.
     */
    public static <T> T suppressExceptions(Function0<T> supplier, String message) {
        try {
            return supplier.apply();
        } catch (Exception e) {
            throw new RuntimeException(message, e);
        }
    }

    /**
     * Helper function to wrap a checked exception as runtime exception. If exception is already a runtime exception,
     * it will be thrown w/o wrapping.
     *
     * @param throwable The throwable to throw as unchecked exception.
     * @param <R>       The return type to make typing happy.
     * @return Any dummy return value. (Nothing will be returned, because Exception will be thrown).
     */
    public static <R> R wrapAndThrow(final Throwable throwable) {
        if (throwable instanceof RuntimeException) {
            throw (RuntimeException) throwable;
        }

        if (throwable instanceof Error) {
            throw (Error) throwable;
        }

        throw new UndeclaredThrowableException(throwable);
    }

}
