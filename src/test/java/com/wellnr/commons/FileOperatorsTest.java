package com.wellnr.commons;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class FileOperatorsTest {

    @Test
    public void testCreateTempFileFromResource() {
        var path = FileOperators.createTempFileFromResource("sample-document.pdf");
        assertTrue(Files.exists(path));
    }

    @Test
    public void testGetMimeTypeFromStream() {
        var type = FileOperators.getMimeType(
            FileOperators.getResourcesAsStream("sample-document.pdf")
        );

        assertEquals("application/pdf", type);
    }

    @Test
    public void testGetMimeTypeFromFile() {
        var path = FileOperators.createTempFileFromResource("sample-document.pdf");
        var type = FileOperators.getMimeType(path);

        assertEquals("application/pdf", type);
    }

}