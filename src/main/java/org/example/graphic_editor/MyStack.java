package org.example.graphic_editor;


public class MyStack {
    private Object[] elements;
    private int size;


    public MyStack() {

        elements = new Object[10];
        size = 0;
    }

    public void push(Object value) {
        if (size ==  elements.length) {
            Object[] newArray = new Object[elements.length * 2];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
        elements[size] = value;
        size++;
    }

    public void remove(int index) {
        if (index >= 0 && index < size) {
            for (int i = index; i < size - 1; i++) {
                elements[i] = elements[i + 1];
            }
            size--;
        }
    }

    public void clear() {
        elements = new Object[10];
        size = 0;
    }

    public int size() {
        return size;
    }

    public Object peek() {
        if (size > 0) {
            return elements[size - 1];
        }
        return null;
    }

    public Object pop() {
        if (size > 0) {
            Object value = elements[size - 1];
            elements[size - 1] = null;
            size--;
            return value;
        }
        return null;
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
