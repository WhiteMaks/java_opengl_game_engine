package ru.java.opengl.core.logger.wrapper.impl;

import org.slf4j.LoggerFactory;
import ru.java.opengl.core.logger.wrapper.Logger;

public class LoggerLogback implements Logger {
    private final org.slf4j.Logger logger;

    public LoggerLogback(Class<?> clazz) {
        logger = LoggerFactory.getLogger(clazz);
    }

    @Override
    public void trace(String string) {
        trace(string, null);
    }

    @Override
    public void trace(String string, Throwable throwable) {
        logger.trace(string, throwable);
    }

    @Override
    public void warn(String string) {
        warn(string, null);
    }

    @Override
    public void warn(String string, Throwable throwable) {
        logger.warn(string, throwable);
    }

    @Override
    public void debug(String string) {
        debug(string, null);
    }

    @Override
    public void debug(String string, Throwable throwable) {
        logger.debug(string, throwable);
    }

    @Override
    public void info(String string) {
        info(string, null);
    }

    @Override
    public void info(String string, Throwable throwable) {
        logger.info(string, throwable);
    }

    @Override
    public void error(String string) {
        error(string, null);
    }

    @Override
    public void error(String string, Throwable throwable) {
        logger.error(string, throwable);
    }
}
