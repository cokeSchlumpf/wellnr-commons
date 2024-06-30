/*
 * (C) Copyright 2024. Licensed under the Apache License, Version 2.0.
 * Author: Michael Wellner (https://github.com/cokeSchlumpf/).
 */
package com.wellnr.commons;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import org.junit.jupiter.api.Test;

class FileOperatorsTest {

    @Test
    public void testCreateTempFileFromResource() {
        var path = FileOperators.createTempFileFromResource("sample-document.pdf");
        assertTrue(Files.exists(path));
    }

    @Test
    public void testGetMimeTypeFromStream() {
        var type =
                FileOperators.getMimeType(
                        FileOperators.getResourcesAsStream("sample-document.pdf"));

        assertEquals("application/pdf", type);
    }

    @Test
    public void testGetMimeTypeFromFile() {
        var path = FileOperators.createTempFileFromResource("sample-document.pdf");
        var type = FileOperators.getMimeType(path);

        assertEquals("application/pdf", type);
    }
}
