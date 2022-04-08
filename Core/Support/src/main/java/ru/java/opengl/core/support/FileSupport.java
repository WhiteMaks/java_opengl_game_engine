package ru.java.opengl.core.support;

import java.io.*;

public class FileSupport {
    private final InputStream file;

    public FileSupport(String pathToFile) throws FileNotFoundException {
        this(new File(pathToFile));
    }

    public FileSupport(File file) throws FileNotFoundException {
        this(new FileInputStream(file));
    }

    public FileSupport(InputStream file) throws FileNotFoundException {
        if (file == null) {
            throw new FileNotFoundException();
        }
        this.file = file;
    }

    public String getTextInFile() {
        StringBuilder textInFile = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                textInFile.append(line).append("\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return textInFile.toString();
    }
}
