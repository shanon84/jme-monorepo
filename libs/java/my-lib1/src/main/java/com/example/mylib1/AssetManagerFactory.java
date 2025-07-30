package com.example.mylib1;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;

@Factory
public class AssetManagerFactory {
    @Singleton
    @Bean
    public AssetManager assetManager(SimpleApplication simpleApplication) {
        return simpleApplication.getAssetManager();
    }
}
