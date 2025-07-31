package com.example.mylib1.material;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

class MtlxConverterServiceTest {
    private MtlxConverterService mtlxConverterService = new MtlxConverterService();

    @Test
    void shouldReturnPath() throws URISyntaxException, IOException {
        String j3m = mtlxConverterService.convert("materialfolder/Rock032_1K-PNG.mtlx");

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("materialfolder/Rock032_1K-PNG.j3m");

        if (!new File(resource.toURI()).exists())
            throw new IllegalArgumentException("file not found: " + "materialfolder/Rock032_1K-PNG.j3m");
        String referenceFile = java.nio.file.Files.readString(new File(resource.toURI()).toPath());

        Assertions.assertEquals(referenceFile, j3m);
    }

}