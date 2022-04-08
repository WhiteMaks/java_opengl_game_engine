package ru.java.opengl.core.logger.wrapper;

public interface Logger {

    void trace(String string);

    void trace(String string, Throwable throwable);

    void warn(String string);

    void warn(String string, Throwable throwable);

    void debug(String string);

    void debug(String string, Throwable throwable);

    void info(String string);

    void info(String string, Throwable throwable);

    void error(String string);

    void error(String string, Throwable throwable);
}
