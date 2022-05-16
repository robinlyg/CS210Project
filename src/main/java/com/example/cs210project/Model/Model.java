package com.example.cs210project.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class Model {
    //FILE_NAMES as public static final
    private static final File RECIPE_FILE = new File("RecipeList.dat");
    private static final File MEAT_FILE = new File("MeatList.dat");
    private static final File PRODUCE_FILE = new File("ProduceList.dat");
    private static final File INGREDIENT_FILE = new File("IngredientList.dat");

    protected static ObservableList<Food> inStock = FXCollections.observableArrayList();

    //bool fileHasData
    public static boolean fileHasData(File file) {
        return file.exists() && file.length() > 1;
    }


    public static ObservableList<Food> populateStock() {

        if (fileHasData(MEAT_FILE)) {
            try
            {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(MEAT_FILE));
                Food[] temp = (Food[]) fileReader.readObject();
                inStock.addAll(temp);
                //fileReader.close();
            }catch(IOException | ClassNotFoundException e)
            {
                System.err.println("Error: " + e.getMessage());
            }
        }

        if (fileHasData(PRODUCE_FILE)) {
            try
            {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(PRODUCE_FILE));
                Food[] temp = (Food[]) fileReader.readObject();
                inStock.addAll(temp);
                fileReader.close();
            }catch(IOException | ClassNotFoundException e)
            {
                System.err.println("Error: " + e.getMessage());
            }
        }
        if (fileHasData(INGREDIENT_FILE)) {
            try
            {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(INGREDIENT_FILE));
                Food[] temp = (Food[]) fileReader.readObject();
                inStock.addAll(temp);
                fileReader.close();
            }catch(IOException | ClassNotFoundException e)
            {
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


    private void saveList(String fileName){
        try{
            File binaryFile = new File(fileName);
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(binaryFile));
            if (fileName.equals(RECIPE_FILE)){
                Recipe[] temp = new Recipe[mAllRecipes.size()];
                for (int i = 0; i < temp.length; i++) {
                    temp[i] = mAllRecipes.get(i);
                }
                fileWriter.writeObject(temp);
            }
            if (fileName.equals(MEAT_FILE)){
                Meat[] temp = new Meat[mAllMeat.size()];
                for (int i = 0; i < temp.length; i++) {
                    temp[i] = mAllMeat.get(i);
                }
                fileWriter.writeObject(temp);
            }
            if (fileName.equals(PRODUCE_FILE)){
                Produce[] temp = new Produce[mAllProduce.size()];
                for (int i = 0; i < temp.length; i++) {
                    temp[i] = mAllProduce.get(i);
                }
                fileWriter.writeObject(temp);
            }
            if (fileName.equals(INGREDIENT_FILE)){
                Ingredient[] temp = new Ingredient[mAllIngredients.size()];
                for (int i = 0; i < temp.length; i++) {
                    temp[i] = mAllIngredients.get(i);
                }
                fileWriter.writeObject(temp);
            }
            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: ");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Unexpected IO error: ");
            e.printStackTrace();
        }
    }

    public static boolean writeToRecipe(ObservableList<Recipe> allRecipeList)
    {
        Recipe[] temp = new Recipe[allRecipeList.size()];
        for(int i = 0; i < temp.length; i++)
        {
            temp[i] = allRecipeList.get(i);
        }
        try
        {
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(RECIPE_FILE));
            fileWriter.writeObject(temp);
            fileWriter.close();
            return true;
        }catch(IOException e){
            System.err.println("Error: " + e.getMessage());
        }
        return false;
    }





    //public static observableList<> populate recipe

    //public static bool write to
}
