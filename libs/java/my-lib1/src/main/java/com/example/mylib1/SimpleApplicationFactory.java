package com.example.mylib1;

import com.jme3.app.SimpleApplication;
import com.jme3.system.AppSettings;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;

@Factory
public class SimpleApplicationFactory {

    @Singleton
    @Bean
    public SimpleApplication simpleApplication(JmeProperties jmeProperties) throws InterruptedException {
        final boolean[] isStarted = new boolean[1];
        SimpleApplication app = new SimpleApplication() {
            @Override
            public void simpleInitApp() {
                isStarted[0] = true;
            }
        };

        AppSettings appSettings = new AppSettings(true);
        appSettings.setTitle(jmeProperties.getTitle());
        appSettings.setResolution(jmeProperties.getResolutionWidth(), jmeProperties.getResolutionHeight());

        app.setShowSettings(true); //Settings dialog not supported on mac
        app.setSettings(appSettings);
        app.start();

        while (!isStarted[0]) {
            Thread.sleep(10);
        }
        return app;
    }
}
