package com.wellnr.commons;

import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Optional;

/**
 * Operators to work with files.
 */
@Slf4j
public final class FileOperators {

    private static final Tika tika = new Tika();

    private FileOperators() {
    }

    /**
     * Returns the file extension of a given file name.
     * An extension is only considered if it is between 1 and 4 characters long.
     *
     * @param fileName The name of the file.
     * @return The extension of the file, or an empty {@link Optional} if the file has no extension.
     */
    public static Optional<String> getFileExtension(String fileName) {
        var lastPoint = fileName.lastIndexOf(".");

        if (lastPoint < 0) {
            return Optional.empty();
        } else {
            var suffix = fileName.substring(lastPoint + 1);
            if (suffix.length() > 0 && suffix.length() < 5) {
                return Optional.of(suffix);
            } else {
                return Optional.empty();
            }
        }
    }

    /**
     * Returns the mime-type of a given file.
     *
     * @param path The file to get the MIME type of.
     * @return The MIME type of the file, or "application/octet-stream" if the MIME type could not be detected.
     */
    public static String getMimeType(Path path) {
        var mimeType = Operators.suppressExceptions(
            () -> tika.detect(path.toFile()),
            "Failed to detect MIME type for file: `" + path.toString() + "`"
        );

        if (Objects.isNull(mimeType)) {
            log.warn("Failed to detect MIME type for file: `" + path + "`");
            mimeType = "application/octet-stream";
        }

        return mimeType;
    }

    /**
     * Returns the mime-type of a given file.
     *
     * @param inputStream The input stream to get the MIME type of. The input stream will be consumed.
     * @return The MIME type of the file, or "application/octet-stream" if the MIME type could not be detected.
     */
    public static String getMimeType(InputStream inputStream) {
        var mimeType = Operators.suppressExceptions(
            () -> tika.detect(inputStream),
            "Failed to detect MIME type from input stream."
        );

        if (Objects.isNull(mimeType)) {
            log.warn("Failed to detect MIME type from input stream.");
            mimeType = "application/octet-stream";
        }

        return mimeType;
    }

    /**
     * Reads a resource from `src/[main|test]/resources` and returns it as an input stream.
     *
     * @param resourceName The name of the resource to be read.
     * @return The input stream of the resource.
     */
    public static InputStream getResourcesAsStream(String resourceName) {
        if (!resourceName.startsWith("/")) { resourceName = "/" + resourceName; }
        return FileOperators.class.getResourceAsStream(resourceName);
    }

    public static Path createTempFileFromResource(String resourceName) {
        return Operators.suppressExceptions(() -> {
            var tmpFile = File.createTempFile(
                "resources",
                "." + getFileExtension(resourceName).orElse(".tmp")
            );

            tmpFile.deleteOnExit();

            try (var is = getResourcesAsStream(resourceName)) {
                Files.copy(is, tmpFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            return tmpFile.toPath();
        });
    }

}
