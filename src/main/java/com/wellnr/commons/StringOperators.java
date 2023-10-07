package com.wellnr.commons;

public final class StringOperators {

    private StringOperators() {

    }

    /**
     * Transforms a camel case string to a human-readable string.
     *
     * @param s The string to transform.
     * @return The human-readable string.
     */
    public static String camelCaseToHumanReadable(String s) {
        var result = s.replaceAll(
            String.format("%s|%s|%s",
                "(?<=[A-Z])(?=[A-Z][a-z])",
                "(?<=[^A-Z])(?=[A-Z])",
                "(?<=[A-Za-z])(?=[^A-Za-z])"
            ),
            " "
        );

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
        return s
            .replaceAll("([a-z0-9])([A-Z])", "$1-$2")
            .replaceAll("([A-Z])([A-Z])(?=[a-z])", "$1-$2")
            .toLowerCase();
    }

    /**
     * Transforms a kebab case string to a camel case string.
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

}
