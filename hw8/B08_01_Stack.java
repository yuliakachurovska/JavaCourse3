package hw8;

import java.util.EmptyStackException;


public class B08_01_Stack<T> {

    private static class Node<T> {
        private T value;
        private Node<T> next;

        Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node<T> top;

    public B08_01_Stack() {
        this.top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(T value) {
        Node<T> newNode = new Node<>(value, this.top);
        this.top = newNode;
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T valueToReturn = this.top.value;
        this.top = this.top.next;
        return valueToReturn;
    }

    public T getTop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return this.top.value;
    }

    public static void main(String[] args) {
    }
}