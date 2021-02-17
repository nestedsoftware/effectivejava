package com.nestedsoftware.generics;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CompileErrors {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("hello", "goodbye", "aloha");
//        safeAdd(strings, "farewell"); //compile error

        Collection<String> titles = Arrays.asList("Macbeth", "Romeo and Juliet");
//        printItems(titles); //compile error

    }

    public static void safeAdd(List<Object> items, Object o) {
        items.add(o);

    }

    public static void wildcardAdd(Set<?> items, Object o) {
//        items.add(o); //compile error
    }

    public static void printItems(Collection<Object> items) {
        for (Object o : items) {
            System.out.println(o);
        }

    }
}
