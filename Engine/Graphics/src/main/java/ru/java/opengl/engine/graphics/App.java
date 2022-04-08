package ru.java.opengl.engine.graphics;

import ru.java.opengl.engine.utils.EngineException;

public abstract class App {
    private final Window window;
    private final Graphics graphics;
    private final FPSCounter fpScounter;

    public App() throws EngineException {
        window = new Window("First Window", 800, 400);
        graphics = window.getGraphics();
        fpScounter = new FPSCounter();
    }

    public void start() throws EngineException {
        while (!window.shouldClose()) {
            fpScounter.startCounter();
            doFrame();
            fpScounter.stopAndPost();
        }
        stop();
    }

    private void doFrame() throws EngineException {
        window.pollEvents();
        input();
        update();
        render();
        graphics.endFrame();
    }

    public abstract void update();

    public abstract void render() throws EngineException;

    public abstract void input();

    private void stop() {
        window.cleanup();
    }

    protected Graphics getGraphics() {
        return graphics;
    }

    protected Window getWindow() {
        return window;
    }

    private class FPSCounter {
        private int startTime;
        private int endTime;
        private int frameTimes = 0;
        private short frames = 0;

        public void startCounter() {
            startTime = (int) System.currentTimeMillis();
        }

        public void stopAndPost() {
            endTime = (int) System.currentTimeMillis();
            frameTimes = frameTimes + endTime - startTime;
            ++frames;
            if (frameTimes >= 1000) {
                window.setTitle("FPS: " + frames);
                frames = 0;
                frameTimes = 0;
            }
        }
    }
}
