package ru.java.opengl.core.settings;

import ru.java.opengl.core.logger.wrapper.Logger;
import ru.java.opengl.core.logger.wrapper.impl.LoggerLogback;
import ru.java.opengl.core.opengl.wrapper.OpenGL;
import ru.java.opengl.core.opengl.wrapper.impl.OpenGLLWJGL;
import ru.java.opengl.core.settings.enums.LoggerLibrary;
import ru.java.opengl.core.settings.enums.OpenGraphicLibrary;

public class GlobalSettings {
    private static GlobalSettings globalSettings;

    private LoggerLibrary loggerLibrary;
    private OpenGraphicLibrary openGraphicLibrary;

    private GlobalSettings() {
        loggerLibrary = LoggerLibrary.LOGBACK;
        openGraphicLibrary = OpenGraphicLibrary.LWJGL;
    }

    public static GlobalSettings getInstance() {
        if (globalSettings != null) {
            return globalSettings;
        }
        synchronized (GlobalSettings.class) {
            if (globalSettings == null) {
                globalSettings = new GlobalSettings();
            }
            return globalSettings;
        }
    }

    public Logger getLogger(Class<?> clazz) {
        switch (loggerLibrary) {
            default -> {
                return new LoggerLogback(clazz);
            }
        }
    }

    public OpenGL getOpenGL() {
        switch (openGraphicLibrary) {
            default -> {
                return new OpenGLLWJGL();
            }
        }
    }

    public void setLoggerLibrary(LoggerLibrary loggerLibrary) {
        this.loggerLibrary = loggerLibrary;
    }

    public void setOpenGraphicLibrary(OpenGraphicLibrary openGraphicLibrary) {
        this.openGraphicLibrary = openGraphicLibrary;
    }
}
