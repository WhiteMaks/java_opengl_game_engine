package ru.java.opengl.engine.control.utils;

import java.util.AbstractMap;

public class MousePosition {
    private final AbstractMap.SimpleEntry<Double, Double> position;

    public MousePosition(Double x, Double y) {
        position = new AbstractMap.SimpleEntry<>(x, y);
    }

    public Double getX() {
        return position.getKey();
    }

    public Double getY() {
        return position.getValue();
    }
}
