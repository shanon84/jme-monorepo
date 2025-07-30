package com.example.mylib1;

import io.micronaut.context.annotation.ConfigurationProperties;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@ConfigurationProperties("jme.app")
public class JmeProperties {
    @NotBlank
    private String title;
    @Min(100)
    private int resolutionWidth;
    @Min(100)
    private int resolutionHeight;
}
