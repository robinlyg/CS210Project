package com.example.cs210project.Contoller;

import com.example.cs210project.Model.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class Controller {

    private static Controller theInstance;
    private ObservableList<Food> mStock;
    private ObservableList<Recipe> mAllRecipes;
//    private ObservableList<Ingredient> mAllIngredients;
//    private ObservableList<Produce> mAllProduce;
//    private ObservableList<Meat> mAllMeat;

   // private String fileName = "";

    private Controller(){}

    public static Controller getInstance()
    {
        if(theInstance == null)
        {
            theInstance = new Controller();
            theInstance.mStock = Model.populateStock();
            theInstance.mAllRecipes = Model.populateRecipeList();

        }
        return theInstance;
    }

    public void saveData(){
        Model.writeStockToBinaryFile(mStock);
        Model.writeRecipeToBinaryFile(mAllRecipes);
    }

    public ObservableList<Food> getAllInStock()
    {
        return mStock;
    }


}
