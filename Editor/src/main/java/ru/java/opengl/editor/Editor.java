package ru.java.opengl.editor;

import ru.java.opengl.engine.graphics.App;
import ru.java.opengl.engine.utils.EngineException;

public class Editor extends App {

    public Editor() throws EngineException {

    }

    @Override
    public void update() {

    }

    @Override
    public void render() throws EngineException {
        getGraphics().clearColor(0.5f, 0, 0.5f, 1);
        getGraphics().drawTestTriangle();
    }

    @Override
    public void input() {

    }
}
