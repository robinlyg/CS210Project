package com.example.cs210project.Model;

import java.util.ArrayList;


public class MainDish extends Dish {

    private Meat meat; //chicken, beef, fish

    public MainDish(String name, ArrayList<Ingredient> ingredients, Meat meatType){
        super(name, ingredients);
        this.meat = meatType;
    }

    public Meat getMeat() {
        return meat;
    }

    public void setMeat(Meat meat) {
        this.meat = meat;
    }

}

