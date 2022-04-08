package ru.java.opengl.engine.models;

import ru.java.opengl.engine.models.exceptions.FileNotOBJFormatException;
import ru.java.opengl.core.support.FileSupport;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileOBJ {
    private final FileSupport fileSupport;

    public FileOBJ(String pathToFileOBJ) throws FileNotFoundException, FileNotOBJFormatException {
        this(new File(pathToFileOBJ));
    }

    public FileOBJ(File fileOBJ) throws FileNotFoundException, FileNotOBJFormatException {
        if (!fileOBJ.getName().toLowerCase().endsWith(".obj")) {
            throw new FileNotOBJFormatException(fileOBJ);
        }
        fileSupport = new FileSupport(fileOBJ);
    }

    public FileOBJ(InputStream fileOBJ) throws FileNotFoundException {
        fileSupport = new FileSupport(fileOBJ);
    }

    public String getTextInFileOBJ() {
        return fileSupport.getTextInFile();
    }
}
