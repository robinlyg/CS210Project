package com.example.cs210project.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;


public class Recipe implements Serializable {

    private String name;
    private MainDish mainIngredient;
    private SideDish sideIngredient;
    private ArrayList<Meat> meats; //chicken, beef, fish
    private ArrayList<Produce> produce;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<String> directions;
    private String preparation; //boiled, grilled, fried, baked

    public Recipe(String name, ArrayList<Meat> meats, ArrayList<Produce> produce, ArrayList<Ingredient> ingredients,
                  ArrayList<String> directions, String preparation) {
        this.name = name;
        this.meats = meats;
        this.produce = produce;
        this.ingredients = ingredients;
        this.directions = directions;
        this.preparation = preparation;
    }

    public Recipe(){
        this.name = "name";
        this.meats = new ArrayList<>();
        this.produce = new ArrayList<>();
        this.ingredients = null;
        this.directions = null;
        this.preparation = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Meat> getMeat() {
        return meats;
    }

    public void setMeat(ArrayList<Meat> meats) {
        this.meats = meats;
    }

    public ArrayList<Produce> getProduce() {
        return produce;
    }

    public void setProduce(ArrayList<Produce> produce) {
        this.produce = produce;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getDirections() {
        return directions;
    }

    public void setDirections(ArrayList<String> directions) {
        this.directions = directions;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;
        Recipe recipe = (Recipe) o;
        return name.equals(recipe.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Recipe";
    }
}

