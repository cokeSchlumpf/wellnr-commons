/*
 * (C) Copyright 2023. Licensed under the Apache License, Version 2.0.
 * Author: Michael Wellner (https://github.com/cokeSchlumpf/).
 */
package com.wellnr.commons;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public final class ReflectionOperators {

    private ReflectionOperators() {}

    /**
     * Returns a list of methods in the given class which are annoted with the given annotation.
     *
     * @param clazz           The class to inspect.
     * @param annotationClass The annotation class to look for.
     * @return A list of methods in the given class which are annoted with the given annotation.
     */
    public static List<Method> getMethodsWithAnnotation(
            Class<?> clazz, Class<? extends Annotation> annotationClass) {

        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(annotationClass))
                .toList();
    }
}
