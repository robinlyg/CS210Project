package com.example.cs210project.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;


public class Recipe implements Serializable {

    private String name;

    public MainDish getMainIngredient() {
        return mainIngredient;
    }

    public void setMainIngredient(MainDish mainIngredient) {
        this.mainIngredient = mainIngredient;
    }

    public SideDish getSideIngredient() {
        return sideIngredient;
    }

    public void setSideIngredient(SideDish sideIngredient) {
        this.sideIngredient = sideIngredient;
    }

    public ArrayList<Meat> getMeats() {
        return meats;
    }

    public void setMeats(ArrayList<Meat> meats) {
        this.meats = meats;
    }

    private MainDish mainIngredient = null;    //meat
    private SideDish sideIngredient = null;    //produce
    private ArrayList<Meat> meats; //chicken, beef, fish
    private ArrayList<Produce> produce; //vegetable, fruit, herb, starch
    private ArrayList<Ingredient> ingredients;
    //private ArrayList<String> directions;
    private String directions;
    private String preparation; //boiled, grilled, fried, baked

    public Recipe(MainDish mainIngredient, String name, ArrayList<Meat> meats, ArrayList<Produce> produce,
                  String directions, String preparation) {
        this.mainIngredient = mainIngredient;
        this.name = name;
        this.meats = meats;
        this.produce = produce;
        this.directions = directions;
        this.preparation = preparation;
    }

    public Recipe(SideDish sideIngredient, String name, ArrayList<Meat> meats, ArrayList<Produce> produce,
                  String directions, String preparation) {
        this.sideIngredient = sideIngredient;
        this.name = name;
        this.meats = meats;
        this.produce = produce;
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


    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public String show() {
        String output = name + "\n\n";
        output += "List of Ingredients Needed:\n";

        if (mainIngredient != null) {
            output += mainIngredient.getMeat().getType() + "\n";
            ingredients = mainIngredient.getIngredients();
        } else {
            output += sideIngredient.getMainIngredient().name + "\n";
            ingredients = sideIngredient.getIngredients();
        }
        for (Meat meat: meats) {
            output += meat.getType() + "\n";
        }
        for (Produce prod: produce) {
            output += prod.name + "\n";
        }
        for (Ingredient ingred: ingredients) {
            output += ingred.getName() + "\n";
        }

        System.out.println("\n");

        return output + directions;
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
        return name;
    }
}

