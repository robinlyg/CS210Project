package com.example.cs210project.Model;

import java.util.ArrayList;


public abstract class Dish {

    protected String name;
    protected ArrayList<Ingredient> ingredients;


    public Dish(String name, ArrayList<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public Dish() {
        name = "Dish Name";
        ingredients = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }


}
