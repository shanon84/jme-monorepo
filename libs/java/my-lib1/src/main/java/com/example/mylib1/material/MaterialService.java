package com.example.mylib1.material;

import com.example.global.annotations.Service;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.texture.Texture;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MaterialService {
    private static final String TEXTURE_FOLDER = "Textures/";
    private static final String ROCK_032_1k_PNG = "Rock032_1K-PNG";

    private static final String COLOR_PNG = "_Color.png";
    private static final String NORMAL_PNG = "_NormalGL.png";
    public static final String ROUGHNESS_PNG = "_Roughness.png";
    public static final String DISPLACEMENT_PNG = "_Displacement.png";

    private final AssetManager assetManager;

    public Material getBlueUnchaded() {
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        return mat;
    }

    public Material getRock032_1K() {
        Material material = assetManager.loadMaterial("Textures/Rock032_1K-PNG/Rock032_1K-PNG.mtlx");
        if (material != null)
            return material;

        Texture albedo = loadTexture(ROCK_032_1k_PNG + "/" + ROCK_032_1k_PNG + COLOR_PNG);
        Texture normalMap = loadTexture(ROCK_032_1k_PNG + "/" + ROCK_032_1k_PNG + NORMAL_PNG);
        Texture roughnessMap = loadTexture(ROCK_032_1k_PNG + "/" + ROCK_032_1k_PNG + ROUGHNESS_PNG);
        Texture heightMap = loadTexture(ROCK_032_1k_PNG + "/" + ROCK_032_1k_PNG + DISPLACEMENT_PNG);

        Material mat = new Material(assetManager, "Common/MatDefs/Light/PBRLighting.j3md");
        mat.setTexture("BaseColorMap", albedo);
        mat.setTexture("NormalMap", normalMap);
        mat.setTexture("RoughnessMap", roughnessMap);
        mat.setTexture("ParallaxMap", heightMap);
        mat.setFloat("Roughness", 1.0f);
        mat.setFloat("ParallaxHeight", 0.1f);
        mat.setColor("Specular", ColorRGBA.White);


        return mat;
    }

    private Texture loadTexture(String name) {
        return assetManager.loadTexture(TEXTURE_FOLDER + name);
    }
}
