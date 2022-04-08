package ru.java.opengl.engine.graphics;

import ru.java.opengl.engine.graphics.exceptions.GraphicsException;
import ru.java.opengl.engine.graphics.exceptions.ShaderException;
import ru.java.opengl.engine.utils.EngineException;
import ru.java.opengl.core.logger.wrapper.Logger;
import ru.java.opengl.core.opengl.wrapper.OpenGL;
import ru.java.opengl.core.settings.GlobalSettings;

import java.nio.FloatBuffer;
import java.nio.charset.StandardCharsets;

public class Graphics {
    private final static Logger logger = GlobalSettings.getInstance().getLogger(Graphics.class);

    private final OpenGL openGL;

    private final String title;

    private final int width;
    private final int height;

    private int shaderProgramId;
    private int vaoId;
    private int vboId;

    private long window;

    public Graphics(final String title, final int width, final int height) {
        openGL = GlobalSettings.getInstance().getOpenGL();

        this.title = title;
        this.width = width;
        this.height = height;
    }

    public long initWindow() throws EngineException {
        logger.debug("Запущена инициализация окна [ " + title + " ] с размером [ " + width + "x" + height + " ]");
        openGL.glfwInit();
        openGL.glfwWindowHint(openGL.GLFW_CONTEXT_VERSION_MAJOR(), 4);
        openGL.glfwWindowHint(openGL.GLFW_CONTEXT_VERSION_MINOR(), 6);
        openGL.glfwWindowHint(openGL.GLFW_OPENGL_PROFILE(), openGL.GLFW_OPENGL_CORE_PROFILE());
        window = openGL.glfwCreateWindow(width, height, title, 0, 0);
        if (window == 0) {
            openGL.glfwTerminate();
            logger.error("Ошибка при создании окна GLFW");
            throw new GraphicsException("Failed to create GLFW window");
        }
        openGL.setGlfwCenterPosition(window);
        openGL.glfwMakeContextCurrent(window);
        openGL.glfwSwapInterval(0);
        openGL.glfwShowWindow(window);
        openGL.createCapabilities();
        logger.debug("Инициализация окна [ " + title + " ] завершена. Идентификатор окна [ " + window + " ]. Версия OpenGL [ " + openGL.glGetString(openGL.GL_VERSION()) + " ]");
        return window;
    }

    public void clearColor(final float red, final float green, final float blue, final float alpha) {
        openGL.glClear(openGL.GL_COLOR_BUFFER_BIT() | openGL.GL_DEPTH_BUFFER_BIT());
        openGL.glClearColor(red, green, blue, alpha);
    }

    public void drawTestTriangle() throws EngineException {


        String vertexShaderCode;
        String fragmentShaderCode;
        try {
            vertexShaderCode = new String(Graphics.class.getResourceAsStream("/shaders/vertices/VertexShader.vs").readAllBytes(), StandardCharsets.UTF_8);
            fragmentShaderCode = new String(Graphics.class.getResourceAsStream("/shaders/fragments/FragmentShader.fs").readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new EngineException(e.getMessage());
        }

        float[] vertices = {
                0.0f, 0.5f, 0.0f,
                0.5f, -0.5f, 0.0f,
                -0.5f, -0.5f, 0.0f,
        };

        float[] colours = {
                0.5f, 0.0f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.0f, 0.0f, 0.5f
        };

        //вершинный шейдер
        int vertexShaderId = openGL.glCreateShader(openGL.GL_VERTEX_SHADER());
        if (vertexShaderId == 0) {
            throw new ShaderException("Ошибка при создании вершинного шейдера");
        }
        logger.debug("Вершинный шейдер создан");
        openGL.glShaderSource(vertexShaderId, vertexShaderCode);
        openGL.glCompileShader(vertexShaderId);
        if (openGL.glGetShaderi(vertexShaderId, openGL.GL_COMPILE_STATUS()) == 0) {
            throw new ShaderException("Ошибка при компиляции вершинного шейдера. " + openGL.glGetShaderInfoLog(vertexShaderId, 1024));
        }
        logger.debug("Вершинный шейдер скомпилирован");

        //фрагментный шейдер
        int fragmentShaderId = openGL.glCreateShader(openGL.GL_FRAGMENT_SHADER());
        if (fragmentShaderId == 0) {
            throw new ShaderException("Ошибка при создании фрагментного шейдера");
        }
        logger.debug("Фрагментный шейдер создан");
        openGL.glShaderSource(fragmentShaderId, fragmentShaderCode);
        openGL.glCompileShader(fragmentShaderId);
        if (openGL.glGetShaderi(fragmentShaderId, openGL.GL_COMPILE_STATUS()) == 0) {
            throw new ShaderException("Ошибка при компиляции фрагментного шейдера. " + openGL.glGetShaderInfoLog(fragmentShaderId, 1024));
        }
        logger.debug("Фрагментный шейдер скомпилирован");

        //шейдерная программа
        shaderProgramId = openGL.glCreateProgram();
        openGL.glAttachShader(shaderProgramId, vertexShaderId);
        openGL.glAttachShader(shaderProgramId, fragmentShaderId);
        openGL.glLinkProgram(shaderProgramId);
        if (openGL.glGetProgrami(shaderProgramId, openGL.GL_LINK_STATUS()) == 0) {
            throw new ShaderException("Ошибка при связывании шейдеров с программой. " + openGL.glGetProgramInfoLog(shaderProgramId, 1024));
        }
        logger.debug("Шейдеры связаны с программой");

        //отчистка ресурсов
        openGL.glDetachShader(shaderProgramId, vertexShaderId);
        openGL.glDetachShader(shaderProgramId, fragmentShaderId);
        openGL.glDeleteShader(vertexShaderId);
        openGL.glDeleteShader(fragmentShaderId);

        //проверка корректности программы
        openGL.glValidateProgram(shaderProgramId);
        if (openGL.glGetProgrami(shaderProgramId, openGL.GL_VALIDATE_STATUS()) == 0) {
            logger.warn("Проверка программы завершилась не успешно. " + openGL.glGetProgramInfoLog(shaderProgramId, 1024));
        }

        vaoId = openGL.glGenVertexArrays();
        openGL.glBindVertexArray(vaoId);

        //вершины
        FloatBuffer vertexBuffer = openGL.memAllocFloat(vertices.length);
        vertexBuffer.put(vertices).flip();
        vboId = openGL.glGenBuffers();
        openGL.glBindBuffer(openGL.GL_ARRAY_BUFFER(), vboId);
        openGL.glBufferData(openGL.GL_ARRAY_BUFFER(), vertexBuffer, openGL.GL_STATIC_DRAW());
        openGL.glEnableVertexAttribArray(0);
        openGL.glVertexAttribPointer(0, 3, openGL.GL_FLOAT(), false, 0, 0);
        openGL.glBindBuffer(openGL.GL_ARRAY_BUFFER(), 0);
        openGL.memFree(vertexBuffer);

        //цвет
        FloatBuffer colourBuffer = openGL.memAllocFloat(colours.length);
        colourBuffer.put(colours).flip();
        vboId = openGL.glGenBuffers();
        openGL.glBindBuffer(openGL.GL_ARRAY_BUFFER(), vboId);
        openGL.glBufferData(openGL.GL_ARRAY_BUFFER(), colourBuffer, openGL.GL_STATIC_DRAW());
        openGL.glEnableVertexAttribArray(1);
        openGL.glVertexAttribPointer(1, 3, openGL.GL_FLOAT(), false, 0, 0);
        openGL.glBindBuffer(openGL.GL_ARRAY_BUFFER(), 0);
        openGL.memFree(colourBuffer);

        openGL.glUseProgram(shaderProgramId);
        openGL.glBindVertexArray(vaoId);
        openGL.glDrawArrays(openGL.GL_TRIANGLES(), 0, 3);

        openGL.glBindVertexArray(0);
        openGL.glUseProgram(0);

        //отчистка
        openGL.glDeleteVertexArrays(vaoId);
        openGL.glDeleteBuffers(vboId);
    }

    public void endFrame() {
        openGL.glfwSwapBuffers(window);
    }

    public OpenGL getOpenGL() {
        return openGL;
    }
}
