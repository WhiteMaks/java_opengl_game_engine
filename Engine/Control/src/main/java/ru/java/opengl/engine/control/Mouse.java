package ru.java.opengl.engine.control;

import ru.java.opengl.engine.control.utils.MousePosition;

import java.util.ArrayDeque;
import java.util.Queue;

public class Mouse {
    private final static int bufferSize = 16;

    private Queue<Event> buffer;

    private double x;
    private double y;

    private boolean leftIsPressed;
    private boolean rightIsPressed;
    private boolean isInWindow;

    public Mouse() {
        buffer = new ArrayDeque<>();

        leftIsPressed = false;
        rightIsPressed = false;
        isInWindow = false;
    }

    public MousePosition getPosition() {
        return new MousePosition(x, y);
    }

    public double getPositionX() {
        return x;
    }

    public double getPositionY() {
        return y;
    }

    public boolean isInWindow() {
        return isInWindow;
    }

    public boolean leftIsPressed() {
        return leftIsPressed;
    }

    public boolean rightIsPressed() {
        return rightIsPressed;
    }

    public Event reed() {
        if (buffer.size() > 0) {
            return buffer.poll();
        } else {
            return new Event();
        }
    }

    public void flush() {
        buffer = new ArrayDeque<>();
    }

    public void onMouseMove(double newX, double newY) {
        x = newX;
        y = newY;

        buffer.add(new Event(Event.Type.MOVE, this));
        trimBuffer();
    }

    public void onMouseLeave() {
        isInWindow = false;

        buffer.add(new Event(Event.Type.LEAVE, this));
        trimBuffer();
    }

    public void onMouseEnter() {
        isInWindow = true;

        buffer.add(new Event(Event.Type.ENTER, this));
        trimBuffer();
    }

    public void onLeftPressed(double x, double y) {
        leftIsPressed = true;

        buffer.add(new Event(Event.Type.L_PRESS, this));
        trimBuffer();
    }

    public void onLeftReleased(double x, double y) {
        leftIsPressed = false;

        buffer.add(new Event(Event.Type.L_RELEASE, this));
        trimBuffer();
    }

    public void onRightPressed(double x, double y) {
        rightIsPressed = true;

        buffer.add(new Event(Event.Type.R_PRESS, this));
        trimBuffer();
    }

    public void onRightReleased(double x, double y) {
        rightIsPressed = false;

        buffer.add(new Event(Event.Type.R_RELEASE, this));
        trimBuffer();
    }

    public void onWheelUp(double x, double y) {
        buffer.add(new Event(Event.Type.WHEEL_UP, this));
        trimBuffer();
    }

    public void onWheelDown(double x, double y) {
        buffer.add(new Event(Event.Type.WHEEL_DOWN, this));
        trimBuffer();
    }

    public void onWheelDelta(double x, double y, double delta) {
        if (delta > 0) {
            onWheelUp(x, y);
        }
        if (delta < 0) {
            onWheelDown(x, y);
        }
    }

    private void trimBuffer() {
        while (buffer.size() > bufferSize) {
            buffer.poll();
        }
    }

    public static class Event {
        private final Type type;

        private final boolean leftIsPressed;
        private final boolean rightIsPressed;

        private final double x;
        private final double y;

        public Event() {
            type = Type.INVALID;

            leftIsPressed = false;
            rightIsPressed = false;

            x = 0;
            y = 0;
        }

        public Event(final Type type, final Mouse parent) {
            this.type = type;

            leftIsPressed = parent.leftIsPressed;
            rightIsPressed = parent.rightIsPressed;

            x = parent.x;
            y = parent.y;
        }

        public boolean isValid() {
            return type != Type.INVALID;
        }

        public Type getType() {
            return type;
        }

        public MousePosition getPosition() {
            return new MousePosition(x, y);
        }

        public double getPositionX() {
            return x;
        }

        public double getPositionY() {
            return y;
        }

        public boolean leftIsPressed() {
            return leftIsPressed;
        }

        public boolean rightIsPressed() {
            return rightIsPressed;
        }

        public enum Type {
            L_PRESS,
            L_RELEASE,

            R_PRESS,
            R_RELEASE,

            WHEEL_UP,
            WHEEL_DOWN,

            MOVE,
            ENTER,
            LEAVE,

            INVALID
        }
    }
}
