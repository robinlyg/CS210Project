package com.example.cs210project.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;


public class Recipe implements Serializable {

    private String name;
    private Meat meat; //chicken, beef, fish
    private Produce produce;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<String> directions;
    private String preparation; //boiled, grilled, fried, baked

    public Recipe(String name, Meat meat, Produce produce, ArrayList<Ingredient> ingredients,
                  ArrayList<String> directions, String preparation) {
        this.name = name;
        this.meat = meat;
        this.produce = produce;
        this.ingredients = ingredients;
        this.directions = directions;
        this.preparation = preparation;
    }

    public Recipe(){
        this.name = "name";
        this.meat = new Meat("meat");
        this.produce = new Produce("produce");
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

    public Meat getMeat() {
        return meat;
    }

    public void setMeat(Meat meat) {
        this.meat = meat;
    }

    public Produce getProduce() {
        return produce;
    }

    public void setProduce(Produce produce) {
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
        return Objects.equals(name, recipe.name) && Objects.equals(meat, recipe.meat)
                && Objects.equals(produce, recipe.produce) && Objects.equals(ingredients, recipe.ingredients)
                && Objects.equals(directions, recipe.directions) && Objects.equals(preparation, recipe.preparation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, meat, produce, ingredients, directions, preparation);
    }

    @Override
    public String toString() {
        return "Recipe";
    }
}

