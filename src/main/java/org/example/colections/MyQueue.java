package org.example.colections;

import java.util.Arrays;

public class MyQueue {
    private Object[] elements;
    private int head;
    private int tail;
    private int size;

    public MyQueue() {
        elements = new Object[10];
        head = 0;
        tail = 0;
        size = 0;
    }

    public void add(Object value) {
        if (size == elements.length) {
            Object[] newElements = new Object[elements.length * 2];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[(head + i) % elements.length];
            }
            elements = newElements;
            head = 0;
            tail = size;
        }
        elements[tail] = value;
        tail = (tail + 1) % elements.length;
        size++;
    }

    public void clear() {
        elements = new Object[10];
        head = 0;
        tail = 0;
        size = 0;
    }

    public int size() {
        return size;
    }

    public Object peek() {
        if (isEmpty()) {
            return null;
        }
        return elements[head];
    }

    public Object poll() {
        if (isEmpty()) {
            return null;
        }
        Object value = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        size--;
        return value;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
