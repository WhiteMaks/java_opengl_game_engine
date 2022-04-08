package ru.java.opengl.engine.models;

import ru.java.opengl.engine.models.exceptions.FileNotOBJFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ModelOBJ {
    private final String name;

    private final float[] vertexCoordinates;
    private final float[] textureCoordinates;
    private final float[] normalCoordinates;

    private final int[] vertexIndices;
    private final int[] textureIndices;
    private final int[] normalIndices;

    public ModelOBJ(String name, String pathToModel) throws FileNotFoundException, FileNotOBJFormatException {
        this(name, new FileOBJ(pathToModel));
    }

    public ModelOBJ(String name, File file) throws FileNotFoundException, FileNotOBJFormatException {
        this(name, new FileOBJ(file));
    }

    public ModelOBJ(String name, InputStream inputStream) throws FileNotFoundException {
        this(name, new FileOBJ(inputStream));
    }

    public ModelOBJ(String name, FileOBJ fileOBJ) {
        this.name = name;

        List<Float> vertexCoordinatesList = new ArrayList<>();
        List<Float> textureCoordinatesList = new ArrayList<>();
        List<Float> normalCoordinatesList = new ArrayList<>();

        List<Integer> vertexIndicesList = new ArrayList<>();
        List<Integer> textureIndicesList = new ArrayList<>();
        List<Integer> normalIndicesList = new ArrayList<>();

        readAndParseFileOBJ(fileOBJ, vertexCoordinatesList, textureCoordinatesList, normalCoordinatesList, vertexIndicesList, textureIndicesList, normalIndicesList);

        vertexCoordinates = saveCoordinatesToArray(vertexCoordinatesList);
        textureCoordinates = saveCoordinatesToArray(textureCoordinatesList);
        normalCoordinates = saveCoordinatesToArray(normalCoordinatesList);

        vertexIndices = saveIndicesToArray(vertexIndicesList);
        textureIndices = saveIndicesToArray(textureIndicesList);
        normalIndices = saveIndicesToArray(normalIndicesList);
    }

    private void readAndParseFileOBJ(FileOBJ fileOBJ, List<Float> vertexCoordinatesList, List<Float> textureCoordinatesList, List<Float> normalCoordinatesList, List<Integer> vertexIndicesList, List<Integer> textureIndicesList, List<Integer> normalIndicesList) {
        String fileOBJText = fileOBJ.getTextInFileOBJ();
        String[] linesFileOBJText = fileOBJText.split("\n");
        for (String lineFileOBJValue : linesFileOBJText) {
            String line = lineFileOBJValue.replaceAll("\\s+", " ");
            addCoordinatesIfNeed("v", line, vertexCoordinatesList);
            addCoordinatesIfNeed("vt", line, textureCoordinatesList);
            addCoordinatesIfNeed("vn", line, normalCoordinatesList);
            addIndicesIfNeed("f", line, vertexCoordinatesList.size() > 0, vertexIndicesList, textureCoordinatesList.size() > 0, textureIndicesList, normalCoordinatesList.size() > 0, normalIndicesList);
        }
    }

    private int[] saveIndicesToArray(List<Integer> indices) {
        int[] array = new int[indices.size()];
        for (int i = 0; i < indices.size(); i++) {
            array[i] = indices.get(i);
        }
        return array;
    }

    private float[] saveCoordinatesToArray(List<Float> coordinatesList) {
        float[] array = new float[coordinatesList.size()];
        for (int i = 0; i < coordinatesList.size(); i++) {
            array[i] = coordinatesList.get(i);
        }
        return array;
    }

    private void addCoordinatesIfNeed(String key, String lineFileOBJValue, List<Float> coordinatesList) {
        if (lineFileOBJValue.toLowerCase().startsWith(key + " ")) {
            for (String values : lineFileOBJValue.substring(key.length() + 1).split(" ")) {
                coordinatesList.add(Float.parseFloat(values));
            }
        }
    }

    private void addIndicesIfNeed(String key, String lineFileOBJValue, boolean haveVertexIndices, List<Integer> vertexIndicesList, boolean haveTextureIndices, List<Integer> textureIndicesList, boolean haveNormalIndices, List<Integer> normalIndicesList) {
        if (lineFileOBJValue.toLowerCase().startsWith(key + " ")) {
            for (String values : lineFileOBJValue.substring(key.length() + 1).split(" ")) {
                if (values.contains("/")) {
                    String[] split = values.split("/");
                    if (haveNormalIndices) {
                        normalIndicesList.add(Integer.parseInt(split[2]));
                    }
                    if (haveTextureIndices) {
                        textureIndicesList.add(Integer.parseInt(split[1]));
                    }
                    if (haveVertexIndices) {
                        vertexIndicesList.add(Integer.parseInt(split[0]));
                    }
                } else {
                    vertexIndicesList.add(Integer.parseInt(values));
                }
            }
        }
    }
}
