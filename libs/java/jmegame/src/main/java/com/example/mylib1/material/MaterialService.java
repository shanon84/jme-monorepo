package com.example.mylib1.material;

import com.example.global.annotations.Service;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MaterialService {
    private static final String MATERIAL_FOLDER = "Materials/";

    private final AssetManager assetManager;
    private final MtlxConverterService converter;

    public Material getBlueUnchaded() {
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        return mat;
    }

    public Material getMaterial(MaterialType type) {
        String j3mPath = MATERIAL_FOLDER + type.getFolderName() + "/" + type.getFolderName() + ".j3m";
        String mtlxPath = MATERIAL_FOLDER + type.getFolderName() + "/" + type.getFolderName() + ".mtlx";
        ClassLoader classLoader = getClass().getClassLoader();
        java.net.URL j3mResource = classLoader.getResource(j3mPath);
        if (j3mResource == null) {
            String j3mContent = converter.convert(mtlxPath);
            try {
                java.net.URL folderUrl = classLoader.getResource(MATERIAL_FOLDER + type.getFolderName());
                if (folderUrl == null)
                    throw new RuntimeException("Zielordner für j3m nicht gefunden im Classpath: " + MATERIAL_FOLDER + type.getFolderName());
                java.io.File outFile = new java.io.File(folderUrl.toURI().getPath(), type.getFolderName() + ".j3m");
                java.nio.file.Files.writeString(outFile.toPath(), j3mContent);
            } catch (Exception e) {
                throw new RuntimeException("Fehler beim Schreiben der j3m Datei", e);
            }
        }
        return assetManager.loadMaterial(j3mPath);
    }
}
