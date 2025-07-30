package com.example.mylib1;

import com.example.mylib1.geometry.GeometryService;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class CoreGameState extends BaseAppState implements GameLogic {
    private final SimpleApplication simpleApplication;
    private final GeometryService geometryService;
    private final AssetManager assetManager;
    private Node gameNode;

    @Override
    public void initialize() {
        Node rootNode = simpleApplication.getRootNode();
        Camera camera = simpleApplication.getCamera();
        camera.setLocation(new Vector3f(0, 0, 10));
        camera.lookAt(Vector3f.ZERO, Vector3f.UNIT_Y);
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(1, 0, 0).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        gameNode = new Node("Game World");
        gameNode.addLight(sun);

        Geometry geom = geometryService.getBlueBox();
        gameNode.attachChild(geom);

        Geometry rock032 = geometryService.getRock032();
        rock032.setLocalTranslation(0, 2, -2);
        gameNode.attachChild(rock032);

        rootNode.attachChild(gameNode);
    }

    @Override
    protected void initialize(Application application) {
        log.info("test");
    }

    @Override
    protected void cleanup(Application application) {

    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
