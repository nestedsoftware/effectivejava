package com.nestedsoftware.builders;

public class BuilderDemo {
    public static void main(String[] args) {
        NyPizza pizza = new NyPizza
                .NyPizzaBuilder(Size.SMALL)
                .addTopping(Topping.SAUSAGE)
                .addTopping(Topping.ONION)
                .build();
        System.out.println(pizza);
    }
}