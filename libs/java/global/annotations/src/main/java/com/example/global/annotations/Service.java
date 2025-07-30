package com.example.global.annotations;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Primary;
import jakarta.inject.Singleton;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Bean
@Primary
@Singleton
public @interface Service {
}
