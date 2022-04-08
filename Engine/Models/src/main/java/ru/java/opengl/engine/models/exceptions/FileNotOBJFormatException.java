package ru.java.opengl.engine.models.exceptions;

import java.io.File;

public class FileNotOBJFormatException extends Exception {

    public FileNotOBJFormatException() {
        this("Файл не формата .obj");
    }

    public FileNotOBJFormatException(File file) {
        this("Файл [ " + file.getName() + " ] не формата .obj");
    }

    public FileNotOBJFormatException(String message) {
        super(message);
    }
}
