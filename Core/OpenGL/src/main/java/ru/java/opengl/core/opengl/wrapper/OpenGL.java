package ru.java.opengl.core.opengl.wrapper;

import ru.java.opengl.core.opengl.wrapper.ext.*;
import ru.maven.module.opengl.wrapper.ext.*;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public interface OpenGL {

    void setGlfwCenterPosition(long window);

    void glfwInit();

    void glfwWindowHint(int hint, int value);

    void glfwTerminate();

    void glfwGetWindowSize(long window, IntBuffer width, IntBuffer height);

    void glfwSetWindowPos(long window, int xpos, int ypos);

    void glfwMakeContextCurrent(long window);

    void glfwSwapInterval(int interval);

    void glfwShowWindow(long window);

    void createCapabilities();

    void glClear(int mask);

    void glClearColor(float red, float green, float blue, float alpha);

    void glShaderSource(int shader, CharSequence string);

    void glCompileShader(int shader);

    void glAttachShader(int program, int shader);

    void glLinkProgram(int program);

    void glDetachShader(int program, int shader);

    void glDeleteShader(int shader);

    void glValidateProgram(int program);

    void glBindVertexArray(int array);

    void glBindBuffer(int target, int buffer);

    void glBufferData(int target, FloatBuffer data, int usage);

    void glEnableVertexAttribArray(int index);

    void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, long pointer);

    void memFree(Buffer ptr);

    void glUseProgram(int program);

    void glDrawArrays(int mode, int first, int count);

    void glDeleteVertexArrays(int array);

    void glDeleteBuffers(int buffer);

    void glfwSwapBuffers(long window);

    void glfwPollEvents();

    void glfwSetWindowTitle(long window, CharSequence title);

    void glfwSetKeyCallback(long window, IGLFWKeyCallback cbfun);

    void glfwSetCharCallback(long window, IGLFWCharCallback cbfun);

    void glfwSetCursorPosCallback(long window, IGLFWCursorPosCallback cbfun);

    void glfwSetCursorEnterCallback(long window, IGLFWCursorEnterCallback cbfun);

    void glfwSetMouseButtonCallback(long window, IGLFWMouseButtonCallback cbfun);

    void glfwSetScrollCallback(long window, IGLFWScrollCallback cbfun);

    void glfwSetFramebufferSizeCallback(long window, IGLFWFramebufferSizeCallback cbfun);

    void glfwSetWindowShouldClose(long window, boolean value);

    void glViewport(int x, int y, int w, int h);

    void glfwFreeCallbacks(long window);

    void glfwDestroyWindow(long window);

    void freeGlfwErrorCallback();

    String glGetString(int name);

    String glGetShaderInfoLog(int shader, int maxLength);

    String glGetProgramInfoLog(int program, int maxLength);

    FloatBuffer memAllocFloat(int size);

    boolean glfwWindowShouldClose(long window);

    long glfwCreateWindow(int width, int height, CharSequence title, long monitor, long share);

    int glCreateProgram();

    int glGenBuffers();

    int glCreateShader(int type);

    int glGetShaderi(int shader, int pname);

    int glGetProgrami(int program, int pname);

    int glGenVertexArrays();

    int GLFW_CONTEXT_VERSION_MAJOR();

    int GLFW_CONTEXT_VERSION_MINOR();

    int GLFW_OPENGL_PROFILE();

    int GLFW_OPENGL_CORE_PROFILE();

    int GLFW_RELEASE();

    int GLFW_PRESS();

    int GLFW_MOUSE_BUTTON_RIGHT();

    int GLFW_MOUSE_BUTTON_LEFT();

    int GLFW_KEY_ESCAPE();

    int GL_VERSION();

    int GL_COLOR_BUFFER_BIT();

    int GL_DEPTH_BUFFER_BIT();

    int GL_VERTEX_SHADER();

    int GL_FRAGMENT_SHADER();

    int GL_COMPILE_STATUS();

    int GL_LINK_STATUS();

    int GL_VALIDATE_STATUS();

    int GL_ARRAY_BUFFER();

    int GL_STATIC_DRAW();

    int GL_FLOAT();

    int GL_TRIANGLES();

}
