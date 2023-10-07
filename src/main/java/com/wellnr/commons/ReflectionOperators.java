package com.wellnr.commons;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public final class ReflectionOperators {

    private ReflectionOperators() {

    }

    public static List<Method> getMethodsWithAnnotation(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        return Arrays
            .stream(clazz.getDeclaredMethods())
            .filter(method -> method.isAnnotationPresent(annotationClass))
            .toList();
    }

}
