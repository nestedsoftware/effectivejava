package com.nestedsoftware.generics;

import java.util.Arrays;

public class ToyArrayList<T> {
    private int _size = 100;
    private int _currentIndex = 0;
    private T[] _items;

    public ToyArrayList() {
        @SuppressWarnings("unchecked") T[] items = (T[]) new Object[_size];
        _items = items;
    }

    public void add(T item) {
        _items[_currentIndex++] = item;
    }

    public T[] toArray(T[] destination) {
        if (destination.length < _size) { //destination too small
            @SuppressWarnings("unchecked") T[] copy = (T[]) Arrays.copyOf(_items, _size, destination.getClass());
            return copy;
        }

        System.arraycopy(_items, 0, destination, 0, _size);
        if (destination.length > _size) {
            destination[_size] = null;
        }

        return destination;
    }

    public String toString() {
        return Arrays.toString(_items);
    }

    public static void main(String[] args) {
        ToyArrayList<String> items = new ToyArrayList<>();
        items.add("hello");
        items.add("goodbye");
        items.add("aloha");

        String[] bigDestination = new String[1000];
        items.toArray(bigDestination);

        System.out.println("bigDestination = " + Arrays.toString(bigDestination));

        String[] smallDestinaton = new String[1];
        String[] copy = items.toArray(smallDestinaton);
        System.out.println("copy = " + Arrays.toString(copy));
        System.out.println("smallDestination = " + Arrays.toString(smallDestinaton));
    }
}
