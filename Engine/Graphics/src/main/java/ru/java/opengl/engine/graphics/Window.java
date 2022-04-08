package ru.java.opengl.engine.graphics;

import ru.java.opengl.core.logger.wrapper.Logger;
import ru.java.opengl.core.opengl.wrapper.OpenGL;
import ru.java.opengl.core.settings.GlobalSettings;
import ru.java.opengl.engine.control.Keyboard;
import ru.java.opengl.engine.control.Mouse;
import ru.java.opengl.engine.control.utils.MousePosition;
import ru.java.opengl.engine.utils.EngineException;

public class Window {
    private final static Logger logger = GlobalSettings.getInstance().getLogger(Window.class);

    private final Graphics graphics;
    private final OpenGL openGL;
    private final Keyboard keyboard;
    private final Mouse mouse;

    private final long window;

    public Window(final String title, final int width, final int height) throws EngineException {
        graphics = new Graphics(title, width, height);
        openGL = graphics.getOpenGL();
        window = graphics.initWindow();
        keyboard = initKeyCallback();
        mouse = initMouseCallback();
        initOtherCallbacks();
    }

    public void pollEvents() {
        openGL.glfwPollEvents();
    }

    public void setTitle(final String title) {
        openGL.glfwSetWindowTitle(window, title);
    }

    public Graphics getGraphics() {
        return graphics;
    }

    private Keyboard initKeyCallback() {
        Keyboard keyboard = new Keyboard();
        openGL.glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (action == openGL.GLFW_RELEASE()) {
                if (key == openGL.GLFW_KEY_ESCAPE()) {
                    openGL.glfwSetWindowShouldClose(window, true);
                }
                keyboard.onKeyReleased(key);
            } else if (action == openGL.GLFW_PRESS()) {
                keyboard.onKeyPressed(key);
            }
        });
        openGL.glfwSetCharCallback(window, (window, codepoint) -> keyboard.onChar(codepoint));
        return keyboard;
    }

    private Mouse initMouseCallback() {
        Mouse mouse = new Mouse();
        openGL.glfwSetCursorPosCallback(window, (window, positionX, positionY) -> mouse.onMouseMove(positionX, positionY));
        openGL.glfwSetCursorEnterCallback(window, (window, entered) -> {
            if (entered) {
                mouse.onMouseEnter();
            } else {
                mouse.onMouseLeave();
            }
        });
        openGL.glfwSetMouseButtonCallback(window, (window, button, action, mods) -> {
            MousePosition position = mouse.getPosition();
            if (button == openGL.GLFW_MOUSE_BUTTON_RIGHT()) {
                if (action == openGL.GLFW_PRESS()) {
                    mouse.onRightPressed(position.getX(), position.getY());
                } else {
                    mouse.onRightReleased(position.getX(), position.getY());
                }
            } else if (button == openGL.GLFW_MOUSE_BUTTON_LEFT()) {
                if (action == openGL.GLFW_PRESS()) {
                    mouse.onLeftPressed(position.getX(), position.getY());
                } else {
                    mouse.onLeftReleased(position.getX(), position.getY());
                }
            }
        });
        openGL.glfwSetScrollCallback(window, (window, xOffset, yOffset) -> {
            MousePosition position = mouse.getPosition();
            mouse.onWheelDelta(position.getX(), position.getY(), yOffset);
        });
        return mouse;
    }

    private void initOtherCallbacks() {
        openGL.glfwSetFramebufferSizeCallback(window, (window, width, height) -> {
            logger.trace("Изменение размера буфера [ " + width + " x " + height + " ]");
            openGL.glViewport(0, 0, width, height);
        });
    }

    public boolean shouldClose() {
        return openGL.glfwWindowShouldClose(window);
    }

    public void cleanup() {
        openGL.glfwFreeCallbacks(window);
        openGL.glfwDestroyWindow(window);
        openGL.glfwTerminate();
        openGL.freeGlfwErrorCallback();
        logger.debug("Ресурсы освобождены");
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public Mouse getMouse() {
        return mouse;
    }
}
