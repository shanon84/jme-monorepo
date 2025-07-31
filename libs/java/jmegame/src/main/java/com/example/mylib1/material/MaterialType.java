package com.example.mylib1.material;

public enum MaterialType {
    ROCK_032_1K("Rock032_1K-PNG");
    private final String folderName;

    MaterialType(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderName() {
        return folderName;
    }
}
