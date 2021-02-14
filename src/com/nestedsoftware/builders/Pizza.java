package com.nestedsoftware.builders;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Pizza {

    final Set<Topping> toppings;

    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        abstract Pizza build();

        protected abstract T self();
    }

    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone();
    }

    @Override
    public String toString() {
        return String.join("\n",
                "class: " + getClass().getName(),
                toppings.stream()
                        .map(t -> "topping: " + t)
                        .collect(Collectors.joining("\n")));
    }
}
