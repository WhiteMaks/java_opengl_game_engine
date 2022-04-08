package ru.maven.module.opengl.wrapper.impl;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import ru.maven.module.opengl.wrapper.OpenGL;
import ru.maven.module.opengl.wrapper.ext.*;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.system.MemoryStack.stackPush;

public class OpenGLLWJGL implements OpenGL {

    @Override
    public void setGlfwCenterPosition(long window) {
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);
            glfwGetWindowSize(window, pWidth, pHeight);
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            if (vidmode != null) {
                glfwSetWindowPos(
                        window,
                        (vidmode.width() - pWidth.get(0)) / 2,
                        (vidmode.height() - pHeight.get(0)) / 2
                );
            }
        }
    }

    @Override
    public void glfwInit() {
        org.lwjgl.glfw.GLFW.glfwInit();
    }

    @Override
    public void glfwWindowHint(int hint, int value) {
        org.lwjgl.glfw.GLFW.glfwWindowHint(hint, value);
    }

    @Override
    public void glfwTerminate() {
        org.lwjgl.glfw.GLFW.glfwTerminate();
    }

    @Override
    public void glfwGetWindowSize(long window, IntBuffer width, IntBuffer height) {
        org.lwjgl.glfw.GLFW.glfwGetWindowSize(window, width, height);
    }

    @Override
    public void glfwSetWindowPos(long window, int xpos, int ypos) {
        org.lwjgl.glfw.GLFW.glfwSetWindowPos(window, xpos, ypos);
    }

    @Override
    public void glfwMakeContextCurrent(long window) {
        org.lwjgl.glfw.GLFW.glfwMakeContextCurrent(window);
    }

    @Override
    public void glfwSwapInterval(int interval) {
        org.lwjgl.glfw.GLFW.glfwSwapInterval(interval);
    }

    @Override
    public void glfwShowWindow(long window) {
        org.lwjgl.glfw.GLFW.glfwShowWindow(window);
    }

    @Override
    public void createCapabilities() {
        GL.createCapabilities();
    }

    @Override
    public void glClear(int mask) {
        org.lwjgl.opengl.GL46.glClear(mask);
    }

    @Override
    public void glClearColor(float red, float green, float blue, float alpha) {
        org.lwjgl.opengl.GL46.glClearColor(red, green, blue, alpha);
    }

    @Override
    public void glShaderSource(int shader, CharSequence string) {
        org.lwjgl.opengl.GL46.glShaderSource(shader, string);
    }

    @Override
    public void glCompileShader(int shader) {
        org.lwjgl.opengl.GL46.glCompileShader(shader);
    }

    @Override
    public void glAttachShader(int program, int shader) {
        org.lwjgl.opengl.GL46.glAttachShader(program, shader);
    }

    @Override
    public void glLinkProgram(int program) {
        org.lwjgl.opengl.GL46.glLinkProgram(program);
    }

    @Override
    public void glDetachShader(int program, int shader) {
        org.lwjgl.opengl.GL46.glDetachShader(program, shader);
    }

    @Override
    public void glDeleteShader(int shader) {
        org.lwjgl.opengl.GL46.glDeleteShader(shader);
    }

    @Override
    public void glValidateProgram(int program) {
        org.lwjgl.opengl.GL46.glValidateProgram(program);
    }

    @Override
    public void glBindVertexArray(int array) {
        org.lwjgl.opengl.GL46.glBindVertexArray(array);
    }

    @Override
    public void glBindBuffer(int target, int buffer) {
        org.lwjgl.opengl.GL46.glBindBuffer(target, buffer);
    }

    @Override
    public void glBufferData(int target, FloatBuffer data, int usage) {
        org.lwjgl.opengl.GL46.glBufferData(target, data, usage);
    }

    @Override
    public void glEnableVertexAttribArray(int index) {
        org.lwjgl.opengl.GL46.glEnableVertexAttribArray(index);
    }

    @Override
    public void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, long pointer) {
        org.lwjgl.opengl.GL46.glVertexAttribPointer(index, size, type, normalized, stride, pointer);
    }

    @Override
    public void memFree(Buffer ptr) {
        MemoryUtil.memFree(ptr);
    }

    @Override
    public void glUseProgram(int program) {
        org.lwjgl.opengl.GL46.glUseProgram(program);
    }

    @Override
    public void glDrawArrays(int mode, int first, int count) {
        org.lwjgl.opengl.GL46.glDrawArrays(mode, first, count);
    }

    @Override
    public void glDeleteVertexArrays(int array) {
        org.lwjgl.opengl.GL46.glDeleteVertexArrays(array);
    }

    @Override
    public void glDeleteBuffers(int buffer) {
        org.lwjgl.opengl.GL46.glDeleteBuffers(buffer);
    }

    @Override
    public void glfwSwapBuffers(long window) {
        org.lwjgl.glfw.GLFW.glfwSwapBuffers(window);
    }

    @Override
    public void glfwPollEvents() {
        org.lwjgl.glfw.GLFW.glfwPollEvents();
    }

    @Override
    public void glfwSetWindowTitle(long window, CharSequence title) {
        org.lwjgl.glfw.GLFW.glfwSetWindowTitle(window, title);
    }

    @Override
    public void glfwSetKeyCallback(long window, IGLFWKeyCallback cbfun) {
        org.lwjgl.glfw.GLFW.glfwSetKeyCallback(window, cbfun);
    }

    @Override
    public void glfwSetCharCallback(long window, IGLFWCharCallback cbfun) {
        org.lwjgl.glfw.GLFW.glfwSetCharCallback(window, cbfun);
    }

    @Override
    public void glfwSetCursorPosCallback(long window, IGLFWCursorPosCallback cbfun) {
        org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback(window, cbfun);
    }

    @Override
    public void glfwSetCursorEnterCallback(long window, IGLFWCursorEnterCallback cbfun) {
        org.lwjgl.glfw.GLFW.glfwSetCursorEnterCallback(window, cbfun);
    }

    @Override
    public void glfwSetMouseButtonCallback(long window, IGLFWMouseButtonCallback cbfun) {
        org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback(window, cbfun);
    }

    @Override
    public void glfwSetScrollCallback(long window, IGLFWScrollCallback cbfun) {
        org.lwjgl.glfw.GLFW.glfwSetScrollCallback(window, cbfun);
    }

    @Override
    public void glfwSetFramebufferSizeCallback(long window, IGLFWFramebufferSizeCallback cbfun) {
        org.lwjgl.glfw.GLFW.glfwSetFramebufferSizeCallback(window, cbfun);
    }

    @Override
    public void glfwSetWindowShouldClose(long window, boolean value) {
        org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose(window, value);
    }

    @Override
    public void glViewport(int x, int y, int w, int h) {
        org.lwjgl.opengl.GL46.glViewport(x, y, w, h);
    }

    @Override
    public void glfwFreeCallbacks(long window) {
        org.lwjgl.glfw.Callbacks.glfwFreeCallbacks(window);
    }

    @Override
    public void glfwDestroyWindow(long window) {
        org.lwjgl.glfw.GLFW.glfwDestroyWindow(window);
    }

    @Override
    public void freeGlfwErrorCallback() {
        GLFWErrorCallback glfwErrorCallback = glfwSetErrorCallback(null);
        if (glfwErrorCallback != null) {
            glfwErrorCallback.free();
        }
    }

    @Override
    public String glGetString(int name) {
        return org.lwjgl.opengl.GL46.glGetString(name);
    }

    @Override
    public String glGetShaderInfoLog(int shader, int maxLength) {
        return org.lwjgl.opengl.GL46.glGetShaderInfoLog(shader, maxLength);
    }

    @Override
    public String glGetProgramInfoLog(int program, int maxLength) {
        return org.lwjgl.opengl.GL46.glGetProgramInfoLog(program, maxLength);
    }

    @Override
    public FloatBuffer memAllocFloat(int size) {
        return MemoryUtil.memAllocFloat(size);
    }

    @Override
    public boolean glfwWindowShouldClose(long window) {
        return org.lwjgl.glfw.GLFW.glfwWindowShouldClose(window);
    }

    @Override
    public long glfwCreateWindow(int width, int height, CharSequence title, long monitor, long share) {
        return org.lwjgl.glfw.GLFW.glfwCreateWindow(width, height, title, monitor, share);
    }

    @Override
    public int glCreateProgram() {
        return org.lwjgl.opengl.GL46.glCreateProgram();
    }

    @Override
    public int glGenBuffers() {
        return org.lwjgl.opengl.GL46.glGenBuffers();
    }

    @Override
    public int glCreateShader(int type) {
        return org.lwjgl.opengl.GL46.glCreateShader(type);
    }

    @Override
    public int glGetShaderi(int shader, int pname) {
        return org.lwjgl.opengl.GL46.glGetShaderi(shader, pname);
    }

    @Override
    public int glGetProgrami(int program, int pname) {
        return org.lwjgl.opengl.GL46.glGetProgrami(program, pname);
    }

    @Override
    public int glGenVertexArrays() {
        return org.lwjgl.opengl.GL46.glGenVertexArrays();
    }

    @Override
    public int GLFW_CONTEXT_VERSION_MAJOR() {
        return GLFW_CONTEXT_VERSION_MAJOR;
    }

    @Override
    public int GLFW_CONTEXT_VERSION_MINOR() {
        return GLFW_CONTEXT_VERSION_MINOR;
    }

    @Override
    public int GLFW_OPENGL_PROFILE() {
        return GLFW_OPENGL_PROFILE;
    }

    @Override
    public int GLFW_OPENGL_CORE_PROFILE() {
        return GLFW_OPENGL_CORE_PROFILE;
    }

    @Override
    public int GLFW_RELEASE() {
        return GLFW_RELEASE;
    }

    @Override
    public int GLFW_PRESS() {
        return GLFW_PRESS;
    }

    @Override
    public int GLFW_MOUSE_BUTTON_RIGHT() {
        return GLFW_MOUSE_BUTTON_RIGHT;
    }

    @Override
    public int GLFW_MOUSE_BUTTON_LEFT() {
        return GLFW_MOUSE_BUTTON_LEFT;
    }

    @Override
    public int GLFW_KEY_ESCAPE() {
        return GLFW_KEY_ESCAPE;
    }

    @Override
    public int GL_VERSION() {
        return GL_VERSION;
    }

    @Override
    public int GL_COLOR_BUFFER_BIT() {
        return GL_COLOR_BUFFER_BIT;
    }

    @Override
    public int GL_DEPTH_BUFFER_BIT() {
        return GL_DEPTH_BUFFER_BIT;
    }

    @Override
    public int GL_VERTEX_SHADER() {
        return GL_VERTEX_SHADER;
    }

    @Override
    public int GL_FRAGMENT_SHADER() {
        return GL_FRAGMENT_SHADER;
    }

    @Override
    public int GL_COMPILE_STATUS() {
        return GL_COMPILE_STATUS;
    }

    @Override
    public int GL_LINK_STATUS() {
        return GL_LINK_STATUS;
    }

    @Override
    public int GL_VALIDATE_STATUS() {
        return GL_VALIDATE_STATUS;
    }

    @Override
    public int GL_ARRAY_BUFFER() {
        return GL_ARRAY_BUFFER;
    }

    @Override
    public int GL_STATIC_DRAW() {
        return GL_STATIC_DRAW;
    }

    @Override
    public int GL_FLOAT() {
        return GL_FLOAT;
    }

    @Override
    public int GL_TRIANGLES() {
        return GL_TRIANGLES;
    }

}
