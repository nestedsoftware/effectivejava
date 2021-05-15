package com.nestedsoftware.generics;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class RecursiveTypeBoundDemo {
    public static void main(String[] args) {
        //works without ? super in ToyCollections
        List<Float> javaLangFloats = Arrays.asList(-3f, 2.2f);
        Float maxJavaLangFloat = ToyCollections.max(javaLangFloats);
        System.out.println("maxJavaLangFloat = " + maxJavaLangFloat);

        //doesn't work without ? super in ToyCollections
         Collection<CustomFloat> customFloats = Arrays.asList(
                new CustomFloat(-3f),
                new CustomFloat(2.2f));
        CustomFloat maxCustomFloat = ToyCollections.max(customFloats);
        System.out.println("maxCustomFloat = " + maxCustomFloat);

        List<CustomFloat> items = new ArrayList<>();
        items.add(new CustomFloat(-3f));
        addToCollection(items, new CustomFloat(2.2f));

        Collection<Object> bagOfItems = new ArrayList<>();
        bagOfItems.add("23");
        bagOfItems.add(3.3);
        addStringToCollection(bagOfItems, "3");
    }

    public static <T extends Comparable<? super T>> void addToCollection(Collection<T> items, T newItem) {
        items.add(newItem);
    }

    public static void addStringToCollection(Collection<? super String> items, String newItem) {
        items.add(newItem);
    }
}

abstract class CustomNumber<T extends Number> implements Comparable<CustomNumber<T>> {
    T _value;

    CustomNumber(@NotNull T value) {
        _value = value;
    }

    @Override
    public int compareTo(@NotNull CustomNumber<T> n) {
        if (this instanceof CustomFloat && n instanceof CustomFloat) {
            if (_value.floatValue() == n._value.floatValue()) {
                return 0;
            }
            return _value.floatValue() > n._value.floatValue() ? 1 : -1;
        }
        throw new RuntimeException("we only support custom floats for now");
    }

    @Override
    public String toString() {
        return String.valueOf(_value);
    }
}

class CustomFloat extends CustomNumber<Float> {
    CustomFloat(@NotNull Float value) {
        super(value);
    }
}


