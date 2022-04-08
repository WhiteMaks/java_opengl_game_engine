package ru.maven.module.control;

import java.util.ArrayDeque;
import java.util.BitSet;
import java.util.Queue;

public class Keyboard {
    private final static int bufferSize = 16;
    private final static int nKeys = 350;

    private Queue<Event> keyBuffer;
    private Queue<Character> charBuffer;

    private final BitSet keyStates;

    private boolean autoRepeatEnabled;

    public Keyboard() {
        keyBuffer = new ArrayDeque<>();
        charBuffer = new ArrayDeque<>();
        keyStates = new BitSet(nKeys);
    }

    public boolean keyIsPressed(final int keycode) {
        return keyStates.get(keycode);
    }

    public Event readKey() {
        if (keyBuffer.size() > 0) {
            return keyBuffer.poll();
        } else {
            return new Event();
        }
    }

    public boolean keyIsEmpty() {
        return keyBuffer.isEmpty();
    }

    public char readChar() {
        if (charBuffer.size() > 0) {
            return charBuffer.poll();
        } else {
            return 0;
        }
    }

    public boolean charIsEmpty() {
        return charBuffer.isEmpty();
    }

    public void flushKey() {
        keyBuffer = new ArrayDeque<>();
    }

    public void flushChar() {
        charBuffer = new ArrayDeque<>();
    }

    public void flush() {
        flushKey();
        flushChar();
    }

    public void enableAutoRepeat() {
        autoRepeatEnabled = true;
    }

    public void disableAutoRepeat() {
        autoRepeatEnabled = false;
    }

    public boolean autoRepeatIsEnabled() {
        return autoRepeatEnabled;
    }

    public void onKeyPressed(final int keycode) {
        keyStates.set(keycode, true);
        keyBuffer.add(new Event(Event.Type.PRESS, keycode));
        trimBuffer(keyBuffer);
    }

    public void onKeyReleased(final int keycode) {
        keyStates.set(keycode, false);
        keyBuffer.add(new Event(Event.Type.RELEASE, keycode));
        trimBuffer(keyBuffer);
    }

    public void onChar(final int keycode) {
        charBuffer.add((char) keycode);
        trimBuffer(charBuffer);
    }

    public void clearState() {
        keyStates.clear();
    }

    private <T> void trimBuffer(Queue<T> buffer) {
        while (buffer.size() > bufferSize) {
            buffer.poll();
        }
    }

    public static class Event {
        private final Type type;

        private final int code;

        public Event() {
            this(Type.INVALID, 0);
        }

        public Event(final Type type, final int code) {
            this.type = type;
            this.code = code;
        }

        public boolean isPress() {
            return type == Type.PRESS;
        }

        public boolean isRelease() {
            return type == Type.RELEASE;
        }

        public boolean isValid() {
            return type != Type.INVALID;
        }

        public int getCode() {
            return code;
        }

        public enum Type {
            PRESS,
            RELEASE,
            INVALID
        }
    }
}
