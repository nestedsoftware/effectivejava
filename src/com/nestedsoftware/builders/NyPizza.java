package com.nestedsoftware.builders;

import java.util.Objects;

class NyPizza extends Pizza {
    private final Size size;

    public static class NyPizzaBuilder extends Pizza.Builder<NyPizzaBuilder> {
        private final Size size;

        public NyPizzaBuilder(Size size) {
            this.size = Objects.requireNonNull(size);
        }

        @Override
        public NyPizza build() {
            return new NyPizza(this);
        }

        @Override
        protected NyPizzaBuilder self() {
            return this;
        }
    }

    @Override
    public String toString() {
        return String.join("\n",
                super.toString(),
                "size: " + size);
    }

    private NyPizza(NyPizzaBuilder builder) {
        super(builder);
        size = builder.size;
    }
}
