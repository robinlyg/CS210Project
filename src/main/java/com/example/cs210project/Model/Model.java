package com.example.cs210project.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.ListIterator;

public class Model {

    private static final File RECIPE_FILE = new File("RecipeList.dat");

    private static final File STOCK_FILE = new File("StockList.dat");

    protected static ObservableList<Food> inStock = FXCollections.observableArrayList();

    public static boolean fileHasData(File file) {
        return file.exists() && file.length() > 1;
    }

    public static ObservableList<Food> populateStock() {

          ObservableList<Food> StockList = FXCollections.observableArrayList();

        if (fileHasData(STOCK_FILE)) {
            try {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(STOCK_FILE));
                Food[] temp = (Food[]) fileReader.readObject();

                inStock.addAll(temp);
                //fileReader.close();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        return inStock;
    }

    public static ObservableList<Recipe> populateRecipeList() {
        ObservableList<Recipe> allRecipes = FXCollections.observableArrayList();
        if (fileHasData(RECIPE_FILE)) {
            try {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(RECIPE_FILE));
                Recipe[] temp = (Recipe[]) fileReader.readObject();
                allRecipes.addAll(temp);
                fileReader.close();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        return allRecipes;
    }

    public static void writeStockToBinaryFile(ObservableList<Food> stockList) {

        try {
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(STOCK_FILE));
            Food[] temp = new Food[stockList.size()];

            for (int i = 0; i < temp.length; i++) {
                temp[i] = stockList.get(i);
            }
            fileWriter.writeObject(temp);
            fileWriter.close();

        } catch (IOException e) {
            System.err.println("Error writing: " + e.getMessage());
        }
    }

    public static boolean writeRecipeToBinaryFile(ObservableList<Recipe> recipeList) {

        try {
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(RECIPE_FILE));
            Recipe[] temp = new Recipe[recipeList.size()];

            for (int i = 0; i < temp.length; i++) {
                temp[i] = recipeList.get(i);
            }
            fileWriter.writeObject(temp);
            fileWriter.close();
            return true;
        } catch (IOException e) {
            System.err.println("Error writing: " + e.getMessage());
        }
        return false;
    }


    public static boolean writeToRecipe(ObservableList<Recipe> allRecipeList) {
        Recipe[] temp = new Recipe[allRecipeList.size()];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = allRecipeList.get(i);
        }
        try {
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(RECIPE_FILE));
            fileWriter.writeObject(temp);
            fileWriter.close();
            return true;
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return false;
    }

}
