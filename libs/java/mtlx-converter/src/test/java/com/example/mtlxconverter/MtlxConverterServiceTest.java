package com.example.mtlxconverter;

import org.junit.jupiter.api.Test;

class MtlxConverterServiceTest {
    private MtlxConverterService mtlxConverterService = new MtlxConverterService();

    @Test
    void shouldReturnPath() {
        mtlxConverterService.convert("Rock032_1K-PNG.mtlx");
    }

}