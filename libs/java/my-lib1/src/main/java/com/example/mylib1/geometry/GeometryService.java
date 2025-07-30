package com.example.mylib1.geometry;

import com.example.global.annotations.Service;
import com.example.mylib1.material.MaterialService;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.util.TangentBinormalGenerator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GeometryService {
    private final MaterialService materialService;

    public Geometry getBlueBox() {
        Box b = new Box(1, 1, 1);
        Geometry geom = new Geometry("Box", b);

        Material mat = materialService.getBlueUnchaded();
        geom.setMaterial(mat);
        return geom;
    }

    public Geometry getRock032() {
        Box b = new Box(2, 2, 2);
        Geometry geom = new Geometry("Box", b);
        TangentBinormalGenerator.generate(geom);
        Material mat = materialService.getRock032_1K();
        geom.setMaterial(mat);
        return geom;
    }
}
