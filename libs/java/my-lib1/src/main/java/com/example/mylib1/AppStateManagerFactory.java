package com.example.mylib1;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;

@Factory
public class AppStateManagerFactory {
    @Singleton
    @Bean
    public AppStateManager appStateManager(SimpleApplication simpleApplication) {
        return simpleApplication.getStateManager();
    }
}
