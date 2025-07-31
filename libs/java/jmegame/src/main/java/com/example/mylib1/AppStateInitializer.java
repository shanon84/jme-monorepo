package com.example.mylib1;

import com.jme3.app.state.AppState;
import com.jme3.app.state.AppStateManager;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class AppStateInitializer {
    private final AppStateManager appStateManager;
    private final Set<AppState> gameStates;

    @EventListener
    public void onStartup(StartupEvent startupEvent) {
        gameStates.forEach(appStateManager::attach);
        gameStates.stream().filter(gameState -> gameState instanceof GameLogic)
                .map(GameLogic.class::cast)
                .forEach(GameLogic::initialize);
    }
}
