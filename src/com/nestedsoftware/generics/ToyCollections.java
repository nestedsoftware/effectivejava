package com.nestedsoftware.generics;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class ToyCollections {
    public static <T extends Comparable<? super T>> T max(@NotNull Collection<T> items) {
        T maxItem = null;
        for (T item : items) {
            if (maxItem == null || item.compareTo(maxItem) > 0) {
                maxItem = item;
            }
        }

        return maxItem;
    }
}
