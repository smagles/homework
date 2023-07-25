package org.example.collections;

public class MyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }


    public void add(Object value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setPrevious(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }


    public void remove(int index) {
        if (index >= 0 && index < size) {
            Node current = getNode(index);
            Node previous = current.getPrevious();
            Node next = current.getNext();

            if (previous != null) {
                previous.setNext(next);
            } else {
                head = next;
            }

            if (next != null) {
                next.setPrevious(previous);
            } else {
                tail = previous;
            }

            size--;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public Object get(int index) {
        if (index >= 0 && index < size) {
            Node node = getNode(index);
            return node.getValue();
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    private Node getNode(int index) {
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }

    private class Node {
        private Object value;
        private Node previous;
        private Node next;

        public Node(Object value) {
            this.value = value;
            previous = null;
            next = null;
        }

        public Object getValue() {
            return value;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Node currentNode = head;
        while (currentNode != null) {
            sb.append(currentNode.value);

            if (currentNode.next != null) {
                sb.append(", ");
            }

            currentNode = currentNode.next;
        }

        sb.append("]");
        return sb.toString();
    }
}
