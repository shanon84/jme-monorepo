package com.example.mylib1.material;

import com.example.global.annotations.Service;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class MtlxConverterService {
    public String convert(String filePath) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL mtlxResource = classLoader.getResource(filePath);
            if (mtlxResource == null) {
                throw new IllegalArgumentException("file not found: " + filePath);
            }
            File mtlxFile = new File(mtlxResource.toURI());
            String mtlxContent = java.nio.file.Files.readString(mtlxFile.toPath());

            // Extrahiere den Ordnernamen/Pfad aus dem filePath
            String folder = "";
            int lastSlash = filePath.lastIndexOf("/");
            if (lastSlash >= 0) {
                folder = filePath.substring(0, lastSlash + 1); // inkl. abschließendem /
            }

            // Extrahiere den Basisnamen (ohne Endung) aus dem Dateinamen
            String fileName = filePath.substring(lastSlash + 1);
            String baseName = fileName;
            int dotIdx = fileName.lastIndexOf(".");
            if (dotIdx > 0) {
                baseName = fileName.substring(0, dotIdx);
            }
            // Ersetze ggf. Bindestriche durch Unterstriche für die Node-Namen
            String nodeBase = baseName.replace('-', '_');

            // Erzeuge die dynamischen Node-Namen
            String colorNode = nodeBase + "_Color";
            String normalNode = nodeBase + "_NormalGL";
            String roughnessNode = nodeBase + "_Roughness";
            String displacementNode = nodeBase + "_Displacement";

            // Extrahiere die Dateinamen aus den <tiledimage> Inputs
            String colorMap = extractFileName(mtlxContent, colorNode);
            String normalMap = extractFileName(mtlxContent, normalNode);
            String roughnessMap = extractFileName(mtlxContent, roughnessNode);
            String displacementMap = extractFileName(mtlxContent, displacementNode);

            // Erzeuge das j3m-Format als String
            String j3m = "Material MyRockMaterial : Common/MatDefs/Light/PBRLighting.j3md {\n" +
                    "    MaterialParameters {\n" +
                    "        BaseColorMap : " + folder + colorMap + "\n" +
                    "        NormalMap : " + folder + normalMap + "\n" +
                    "        RoughnessMap : " + folder + roughnessMap + "\n" +
                    "        ParallaxMap : " + folder + displacementMap + "\n" +
                    "\n" +
                    "        BaseColor : 1 1 1 1\n" +
                    "        Roughness : 1.0\n" +
                    "        Metallic : 0.0\n" +
                    "\n" +
                    "        ParallaxHeight : 0.05\n" +
                    "    }\n" +
                    "    AdditionalRenderState {\n" +
                    "        FaceCull Off\n" +
                    "    }\n" +
                    "}\n";
            return j3m;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String extractFileName(String xml, String nodeName) {
        // Suche nach <tiledimage ... name="nodeName" ...>
        String regex = "<tiledimage[^>]*name=\"" + nodeName + "\"[^>]*>(.*?)</tiledimage>";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex, java.util.regex.Pattern.DOTALL);
        java.util.regex.Matcher matcher = pattern.matcher(xml);
        if (matcher.find()) {
            String tiledImageBlock = matcher.group(1);
            // Suche nach <input name="file" value="..." ... />
            java.util.regex.Pattern filePattern = java.util.regex.Pattern.compile("<input[^>]*name=\"file\"[^>]*value=\"([^\"]+)\"");
            java.util.regex.Matcher fileMatcher = filePattern.matcher(tiledImageBlock);
            if (fileMatcher.find()) {
                return fileMatcher.group(1);
            }
        }
        return "";
    }
}
