package com.nestedsoftware.generics;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class RecursiveTypeBoundDemo {
    public static void main(String[] args) {
        //works without ? super in ToyCollections
        List<Float> javaLangFloats = Arrays.asList(-3f, 2.2f);
        Float maxJavaLangFloat = ToyCollections.max(javaLangFloats);
        System.out.println("maxJavaLangFloat = " + maxJavaLangFloat);

        //doesn't work without ? super in ToyCollections
         List<CustomFloat> customFloats = Arrays.asList(
                new CustomFloat(-3f),
                new CustomFloat(2.2f));
        CustomFloat maxCustomFloat = ToyCollections.max(customFloats);
        System.out.println("maxCustomFloat = " + maxCustomFloat);
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


