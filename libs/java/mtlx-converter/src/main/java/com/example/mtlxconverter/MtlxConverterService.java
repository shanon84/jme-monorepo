package com.example.mtlxconverter;

import com.example.global.annotations.Service;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class MtlxConverterService {
    public String convert(String filePath) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(filePath);

        try {
            if (!new File(resource.toURI()).exists())
                throw new IllegalArgumentException("file not found: " + filePath);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
