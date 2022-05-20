package com.example.cs210project.Model;

import java.util.ArrayList;

public class SideDish extends Dish{

    private Produce mainIngredient;

    public SideDish(String name, ArrayList<Ingredient> ingredients, Produce mainIngredient){
        super(name, ingredients);
        this.mainIngredient = mainIngredient;
    }

    public Produce getMainIngredient() {
        return mainIngredient;
    }

    public void setMainIngredient(Produce mainIngredient) {
        this.mainIngredient = mainIngredient;
    }
}

