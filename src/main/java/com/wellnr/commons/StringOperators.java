/*
 * (C) Copyright 2023. Licensed under the Apache License, Version 2.0.
 * Author: Michael Wellner (https://github.com/cokeSchlumpf/).
 */
package com.wellnr.commons;

import com.wellnr.commons.functions.Function0;

public final class StringOperators {

    private StringOperators() {}

    /**
     * Transforms a camel case string to a human-readable string.
     *
     * @param s The string to transform.
     * @return The human-readable string.
     */
    public static String camelCaseToHumanReadable(String s) {
        var result =
                s.replaceAll(
                        String.format(
                                "%s|%s|%s",
                                "(?<=[A-Z])(?=[A-Z][a-z])",
                                "(?<=[^A-Z])(?=[A-Z])",
                                "(?<=[A-Za-z])(?=[^A-Za-z])"),
                        " ");

        if (result.length() > 1) {
            return result.substring(0, 1).toUpperCase() + result.substring(1);
        } else {
            return result.toUpperCase();
        }
    }

    /**
     * Transforms a camel case string to a kebab case string.
     *
     * @param s The string to transform.
     * @return The transformed string.
     */
    public static String camelCaseToKebabCase(String s) {
        return s.replaceAll("([a-z0-9])([A-Z])", "$1-$2")
                .replaceAll("([A-Z])([A-Z])(?=[a-z])", "$1-$2")
                .toLowerCase();
    }

    /**
     * Compares two strings. Removes leading and trailing whitespaces and only does a case-insensitive comparison.
     *
     * @param s1 The string to check.
     * @param s2 The other string to check.
     * @return The transformed string.
     */
    public static boolean fuzzyEquals(String s1, String s2) {
        return s1.strip().equalsIgnoreCase(s2.strip());
    }

    /**
     * Converts a string to a camel case string.
     *
     * @param s The text to convert.
     * @return A camel case string.
     */
    public static String stringToCamelCase(String s) {
        var builder = new StringBuilder();
        char delimiter = ' ';
        boolean shouldConvertNextCharToLower = true;

        // Replace all special characters with whitespace
        s = s.replaceAll("[^a-zA-Z0-9]", " ");

        // Replace all whitespace with a single space
        s = s.replaceAll("\\s+", " ");

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (currentChar == delimiter) {
                shouldConvertNextCharToLower = false;
            } else if (shouldConvertNextCharToLower) {
                builder.append(Character.toLowerCase(currentChar));
            } else {
                builder.append(Character.toUpperCase(currentChar));
                shouldConvertNextCharToLower = true;
            }
        }

        return builder.toString();
    }

    /**
     * Converts a string to a kebab case string.
     *
     * @param s The string to convert
     * @return A kebab case string.
     */
    public static String stringToKebabCase(String s) {
        return camelCaseToKebabCase(stringToCamelCase(s));
    }

    /**
     * Returns a tech-friendly name from any string. The string will be kebab cased.
     *
     * @param s The text to convert.
     * @return A tech-friendly string.
     */
    public static String stringToTechFriendlyName(String s) {
        return camelCaseToKebabCase(stringToCamelCase(s));
    }

    /**
     * English word pluralization.
     *
     * @param word The word to pluralize.
     * @return The pluralized word.
     */
    public static String pluralize(String word) {
        if (word.endsWith("y")) {
            return word.substring(0, word.length() - 1) + "ies";
        } else {
            return word + "s";
        }
    }

    /**
     * Validates a kebab case string, throws the specified exception if the string is not a kebab case string.
     *
     * @param s   The string to validate.
     * @param e   The exception to throw if the string is not a kebab case string.
     * @param <T> The type of the exception to throw.
     * @return The validated string (without modification).
     * @throws T The exception which is thrown if the string is not a kebab case string.
     */
    public static <T extends Exception> String validateKebabCaseString(String s, Function0<T> e)
            throws T {
        if (!s.matches("^[a-z]+(-[a-z0-9]+)*$")) {
            throw e.get();
        }

        return s;
    }

    /**
     * Validates a kebab case string, throws an exception if the string is not a kebab case string.
     *
     * @param s The string to validate.
     * @return The validated string (without modification).
     * @throws IllegalArgumentException The exception which is thrown if the string is not a kebab case string.
     */
    public static String validateKebabCaseString(String s) {
        validateKebabCaseString(
                s,
                () ->
                        new IllegalArgumentException(
                                String.format("`%s` is not a kebab case string", s)));

        return s;
    }
}
