package ru.java.opengl.editor;

import ru.java.opengl.engine.utils.EngineException;

public class StartEditor {

    public static void main(String[] args) {
        try {
            new Editor().start();
        } catch (EngineException e) {
            e.printStackTrace();
        }
    }
}
